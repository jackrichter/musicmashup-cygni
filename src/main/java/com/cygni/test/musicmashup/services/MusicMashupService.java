package com.cygni.test.musicmashup.services;

import com.cygni.test.musicmashup.models.coverart.Image;
import com.cygni.test.musicmashup.models.musicbrainz.MusicInfo;
import com.cygni.test.musicmashup.models.musicbrainz.Relation;
import com.cygni.test.musicmashup.models.musicbrainz.ReleaseGroup;
import com.cygni.test.musicmashup.models.musicbrainz.Url;
import com.cygni.test.musicmashup.models.response.Album;
import com.cygni.test.musicmashup.models.response.DetailedResponse;
import com.cygni.test.musicmashup.models.wikidata.Enwiki;
import com.cygni.test.musicmashup.models.wikipedia.Page;
import lombok.Data;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Service
@SuppressWarnings("ALL")
public class MusicMashupService {

    private WebClient webClient;
    private RestTemplate restTemplate;
    private Mono<MusicInfo> musicInfoMono;
    private Mono<Enwiki> wikiDataMono;
    private Mono<Page> wikipediaMono;
    private DetailedResponse response;
    private final List<ReleaseGroup> releaseGroupList = new ArrayList<>();
    private final List<String> imageUrlList = new ArrayList<>();

    public MusicMashupService(WebClient webClient, RestTemplate restTemplate, DetailedResponse detailedResponse) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
        this.response = detailedResponse;
    }

    /**
     * Handle MusicBrainz API call, non-blocking, asynchrounosly.
     *
     * @param mbId
     * @return a single reactive object
     */
    public Mono<MusicInfo> getMusicBrainzInfo(String mbId) {
        Mono<MusicInfo> infoMono = this.webClient
                .get()
                .uri("https://musicbrainz.org/ws/2/artist/" + mbId + "?&fmt=json&inc=url-rels+release-groups")
                .retrieve()
                .bodyToMono(MusicInfo.class);

        this.setMusicInfoMono(infoMono);

        // Extract ReleaseGroup object, but restricted to two objects only! In order to save time and make the app more reactive
        getTwoReleaseGroupObjects();

        return infoMono;
    }

    /**
     * Handle WikiData API call, non-blocking, asynchrounosly.
     *
     * @return a single reactive object
     */
    public Mono<Enwiki> getWikiDataInfo() {
        String qId = parseWikiDataId(Objects.requireNonNull(this.getMusicInfoMono().block(), "Error while retrieving WikiData"));

        Mono<Enwiki> enwiki = this.webClient
                .get()
                .uri("https://www.wikidata.org/w/api.php?action=wbgetentities&ids=" + qId +
                        "&format=json&props=sitelinks")
                .retrieve()
                .bodyToMono(Enwiki.class);

        this.setWikiDataMono(enwiki);

        return enwiki;
    }

    /**
     * Handle Wikipedia API call, non-blocking, asynchrounosly.
     *
     * @return a single reactive object
     */
    public Mono<Page> getWikipediaInfo() {
        // We need to URL-encode the Title before using it
        String encodedTitle = fetchUrlEncodedTitle();

        Mono<Page> page = this.webClient
                .get()
                .uri("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles=" +
                        encodedTitle)
                .retrieve()
                .bodyToMono(Page.class);

        this.setWikipediaMono(page);

        return page;
    }

    /**
     * Handle the CoverArt API call, synchronously.
     * The reactive Webclient in Spring's WebFlux project cannot handle redirections at this point!
     */
    public void getCoverArtInfo() {
        // Fetch CoverArt MBIDs
        final List<String> mbidList = fetchMBIDForCoverArt();

        // Construct the Covert Art's URLs
        final List<String> coverArtURLList  = mbidList.stream()
                .map(s -> "https://coverartarchive.org/release-group/" + s)
                .toList();

        // Get max two Image objects in order to avoid long waiting time in the application
        List<Image> imageObjectList = new ArrayList<>();
        for (String s : coverArtURLList) {
            // Some urls in CovertArt service are incorrect
            try {
                imageObjectList.add(this.restTemplate.getForObject(s, Image.class));
            } catch (HttpClientErrorException e) {
                System.out.println("There is an error in the URL: " + s);
            }
        }

        // We only need the Image url string found in the additionalProperties map
        for (Image obj : imageObjectList) {
            List<String> result = getFirstImageUrl(obj.getAdditionalProperties());
            this.imageUrlList.addAll(result);
        }
    }

    /**
     * Build the response of all the services calls.
     */
    public DetailedResponse getResponse() {
        MusicInfo musicInfo = getMusicInfoMono().block(Duration.ofMillis(1000));
        // Set MBID
//        this.response.setMbid(this.musicInfoMono.block().getId());
        this.response.setMbid(Objects.requireNonNull(musicInfo, "Error getting musicInfo").getId());

        // Set Description
        Map<String, Object> wikipidiaMap = this.wikipediaMono.block().getAdditionalProperties();
        this.response.setDescription(this.getArtistDescription(wikipidiaMap));

        // Create Album objects list and add just image urls in this step
        List<Album> albumList = this.imageUrlList.stream()
                .map(imageUrl -> {
                    Album album = new Album();
                    album.setImage(imageUrl);
                    return album;
                })
                .toList();

        // Set rest of data into each album
        for (int i = 0; i < albumList.size(); i++) {
            albumList.get(i).setId(releaseGroupList.get(1).getId());
            albumList.get(i).setTitle(releaseGroupList.get(i).getTitle());
        }

        // Add list of album to Response
        this.response.setAlbums(albumList);

        return response;
    }

    /**
     * Help method to handle extracting the first image url of each Image object.
     *
     * @param outerMap
     * @return List of image url string
     */
    @SuppressWarnings("unchecked")
    private List<String> getFirstImageUrl(Map<String, Object> outerMap) {

        // Retrieve the first image url, only, from each Image object
        return outerMap.entrySet().stream()
                .filter(s -> s.getKey().equalsIgnoreCase("images"))
                .flatMap(t -> ((List<Map<String, Object>>)t.getValue()).stream())
                .map(t -> t.get("image").toString())
                .limit(1)
                .toList();
    }

    /**
     * Parse out the "Q-Id" needed for calling the WikiData API.
     *
     * @param musicInfo
     * @return the Q-Id value
     */
    private String parseWikiDataId(MusicInfo musicInfo) {
        final Relation relation = musicInfo.getRelations().stream()
                .filter(x -> x.getType().equalsIgnoreCase("wikidata"))
                .findFirst()
                .orElse(null);

        Url relationUrl = Objects.requireNonNull(relation, "No URL available").getUrl();
        String resource = relationUrl.getResource();

        return resource.substring(resource.lastIndexOf(("/")) + 1);
    }

    /**
     * Parse the enWiki data structure and extract the artist's Title.
     * The parsed title has to be URL-encoded in order to use it to call the Wikipedia API.
     *
     * @return the URL-encoded title
     */
    private String fetchUrlEncodedTitle() {
        Enwiki enwiki = this.wikiDataMono.block(Duration.ofMillis(1000));
        Map<String, Object> additionalProperties = Objects.requireNonNull(enwiki, "Enwiki link is not available")
                .getAdditionalProperties();

        // Chain flatMap operations to get the title which is the innermost string value
        final Optional<Map.Entry<String, Object>> title = additionalProperties.entrySet().stream()
                .filter(s -> s.getKey().equalsIgnoreCase("entities"))
                .flatMap(entry1 -> ((Map<String, Object>) entry1.getValue()).entrySet().stream())
                .filter(s -> s.getKey().startsWith("Q"))
                .flatMap(entry2 -> ((Map<String, Object>) entry2.getValue()).entrySet().stream())
                .filter(s -> s.getKey().equalsIgnoreCase("sitelinks"))
                .flatMap(entry3 -> ((Map<String, Object>) entry3.getValue()).entrySet().stream())
                .filter(s -> s.getKey().equalsIgnoreCase("enwiki"))
                .flatMap(entry4 -> ((Map<String, Object>) entry4.getValue()).entrySet().stream())
                .filter(s -> s.getKey().equalsIgnoreCase("title"))
                .findFirst();

        // URL-encode the title
        String encodedTitle = "";
        if (title.isPresent()) {
            encodedTitle = title.get().getValue().toString();
        }
        encodedTitle = encodedTitle.replace(" ", "+");

        return encodedTitle;
    }

    /**
     * Parse the Wikipedia's description of the artist.
     *
     * @param wikipidiaInfoMap
     * @return the sought description
     */
    private String getArtistDescription(Map<String, Object> wikipidiaInfoMap) {

        // Chain flatMap operations to get the description which is the innermost string value
        final Optional<Object> value = wikipidiaInfoMap.entrySet().stream()
                .filter(s -> s.getKey().equalsIgnoreCase("query"))
                .flatMap(entry1 -> ((Map<String, Object>) entry1.getValue()).entrySet().stream())
                .filter(s -> s.getKey().equalsIgnoreCase("pages"))
                .flatMap(entry2 -> ((Map<String, Object>) entry2.getValue()).entrySet().stream())
                .filter(s -> Character.isDigit(s.getKey().charAt(0)))
                .flatMap(entry3 -> ((Map<String, Object>) entry3.getValue()).entrySet().stream())
                .filter(s -> s.getKey().equalsIgnoreCase("extract"))
                .map(Map.Entry::getValue)
                .findFirst();

        // Remove multiple newLines and Html tags
        String description = "";
        if (value.isPresent()) {
            description = value.get().toString();
            description.replaceAll("\n", "");
            description = Jsoup.parse(description).text();
        }

        return description;
    }

    /**
     * This action regulates the final response scope for this application to two Albums only!
     * Due to the very long time it takes for each CoverArt service call.
     * Thus, for this application demonstration, it is enough with two albums.
     */
    private void getTwoReleaseGroupObjects() {
        MusicInfo musicInfo = getMusicInfoMono().block(Duration.ofMillis(1000));
//        final List<ReleaseGroup> releaseGroups = getMusicInfoMono().block().getReleaseGroups()
        final List<ReleaseGroup> releaseGroups = Objects.requireNonNull(musicInfo, "Could not get musicInfo").getReleaseGroups()
                .stream()
                .limit(2)
                .collect(Collectors.toList());

        this.releaseGroupList.addAll(releaseGroups);
    }

    /**
     * Helper method to extract the MBID for calling the CoverArt API.
     *
     * @return MBID
     */
    private List<String> fetchMBIDForCoverArt() {
        // Get MBID from the ReleaseGroup objects
        return releaseGroupList.stream()
                .map(ReleaseGroup::getId)
                .toList();
    }
}
