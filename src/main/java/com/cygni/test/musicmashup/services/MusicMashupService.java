package com.cygni.test.musicmashup.services;

import com.cygni.test.musicmashup.models.coverart.CoverArt;
import com.cygni.test.musicmashup.models.coverart.Image;
import com.cygni.test.musicmashup.models.musicbrainz.MusicInfo;
import com.cygni.test.musicmashup.models.musicbrainz.Relation;
import com.cygni.test.musicmashup.models.musicbrainz.ReleaseGroup;
import com.cygni.test.musicmashup.models.musicbrainz.Url;
import com.cygni.test.musicmashup.models.wikidata.Enwiki;
import com.cygni.test.musicmashup.models.wikipedia.Page;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@SuppressWarnings("BlockingMethodInNonBlockingContext")
@Service
@Data
public class MusicMashupService {

    private WebClient webClient;
    private RestTemplate restTemplate;
    private Mono<MusicInfo> musicInfoMono;
    private Mono<Enwiki> wikiDataMono;
    private Mono<Page> wikipediaMono;
    private final List<ReleaseGroup> releaseGroupList = new ArrayList<>();
    private List<Image> imageList = new ArrayList<>();

    public MusicMashupService(WebClient webClient, RestTemplate restTemplate) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
    }

    public Mono<MusicInfo> getMusicBrainzInfo(String mbId) {
        Mono<MusicInfo> info = this.webClient
                .get()
                .uri("https://musicbrainz.org/ws/2/artist/" + mbId + "?&fmt=json&inc=url-rels+release-groups")
                .retrieve()
                .bodyToMono(MusicInfo.class);

        this.setMusicInfoMono(info);

        return info;
    }

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

    public Mono<Page> getWikipediaInfo() {
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

    public void getCoverArtInfo() {
        // Fetch CoverArt MBIDs
        final List<String> mbidList = fetchMBIDForCoverArt();

        // Construct the Covert Art's URLs
        final List<String> coverArtURLList  = mbidList.stream()
                .map(s -> "https://coverartarchive.org/release-group/" + s)
                .toList();

        // Get max two coverArt objects in order to avoid long waiting time in the application
        int max = Math.min(coverArtURLList.size(), 2);
        List<CoverArt> coverArtList = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            try {
                coverArtList.add(this.restTemplate.getForObject(coverArtURLList.get(i), CoverArt.class));
            } catch (HttpClientErrorException e) {
                System.out.println("There is an error in the URL: " + coverArtURLList.get(i));
            }
        }
    }

    private String parseWikiDataId(MusicInfo musicInfo) {
        final Relation relation = musicInfo.getRelations().stream()
                .filter(x -> x.getType().equalsIgnoreCase("wikidata"))
                .findFirst()
                .orElse(null);

        Url relationUrl = Objects.requireNonNull(relation, "No URL available").getUrl();
        String resource = relationUrl.getResource();

        return resource.substring(resource.lastIndexOf(("/")) + 1);
    }

    @SuppressWarnings("unchecked")
    private String fetchUrlEncodedTitle() {
        Enwiki enwiki = this.wikiDataMono.block();
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

    private List<String> fetchMBIDForCoverArt() {
        // Get all ReleaseGroup objects and extract it's MBIDs
        releaseGroupList.addAll(Objects.requireNonNull(getMusicInfoMono().block()).getReleaseGroups());
        return releaseGroupList.stream()
                .map(ReleaseGroup::getId)
                .toList();
    }
}
