package com.cygni.test.musicmashup.services;

import com.cygni.test.musicmashup.models.musicbrainz.MusicInfo;
import com.cygni.test.musicmashup.models.musicbrainz.Relation;
import com.cygni.test.musicmashup.models.musicbrainz.Url;
import com.cygni.test.musicmashup.models.wikidata.Enwiki;
import com.cygni.test.musicmashup.models.wikipedia.Page;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@SuppressWarnings("BlockingMethodInNonBlockingContext")
@Service
@Data
public class MusicMashupService {

    private WebClient webClient;
    private Mono<MusicInfo> musicInfoMono;
    private Mono<Enwiki> wikiDataMono;
    private Mono<Page> wikipediaMono;

    public MusicMashupService(WebClient webClient) {
        this.webClient = webClient;
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

        String returnValue = "";
        for (Map.Entry<String, Object> entry1 : additionalProperties.entrySet()) {
            if (entry1.getKey().equalsIgnoreCase("entities")) {
                Map<String, Object> secondValue = (Map<String, Object>) entry1.getValue();
                for (Map.Entry<String, Object> entry2 : secondValue.entrySet()) {
                    if (entry2.getKey().startsWith("Q")) {
                        Map<String, Object> thirdValue = (Map<String, Object>) entry2.getValue();
                        for (Map.Entry<String, Object> entry3 : thirdValue.entrySet()) {
                            if (entry3.getKey().equalsIgnoreCase("sitelinks")) {
                                Map<String, Object> links = (Map<String, Object>) entry3.getValue();
                                for (Map.Entry<String, Object> entry4 : links.entrySet()) {
                                    if (entry4.getKey().equalsIgnoreCase("enwiki")) {
                                        Map<String, Object> fifthValue = (Map<String, Object>) entry4.getValue();
                                        for (Map.Entry<String, Object> entry5 : fifthValue.entrySet()) {
                                            if (entry5.getKey().equalsIgnoreCase("title")) {
                                                returnValue = (String) entry5.getValue();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // URL-encode the title
        returnValue = returnValue.replace(" ", "+");

        return returnValue;
    }
}
