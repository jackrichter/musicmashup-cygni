package com.cygni.test.musicmashup.services;

import com.cygni.test.musicmashup.models.musicbrainz.MusicInfo;
import com.cygni.test.musicmashup.models.musicbrainz.Relation;
import com.cygni.test.musicmashup.models.musicbrainz.Url;
import com.cygni.test.musicmashup.models.wikidata.Enwiki;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Data
public class MusicMashupService {

    private WebClient webClient;
    private Mono<MusicInfo> musicInfoMono;
    private Mono<Enwiki> wikiDataMono;

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
        String qId = parseWikiDataId(this.getMusicInfoMono().block());
        String wikiDataUri = new StringBuilder("https://www.wikidata.org/w/api.php?action=wbgetentities&ids=").append(qId)
                .append("&format=json&props=sitelinks").toString();
//        System.out.println("wikiDataUri: " + wikiDataUri);
        Mono<Enwiki> enwiki = this.webClient
                .get()
                .uri(wikiDataUri)
                .retrieve()
                .bodyToMono(Enwiki.class);

        this.setWikiDataMono(enwiki);

        return enwiki;
    }

    private String parseWikiDataId(MusicInfo musicInfo) {
        final Relation relation = musicInfo.getRelations().stream()
                .filter(x -> x.getType().equalsIgnoreCase("wikidata"))
                .findFirst()
                .orElse(null);

//        System.out.println(relation);

        Url relationUrl = relation.getUrl();
        String resource = relationUrl.getResource();
        String qId = resource.substring(resource.lastIndexOf(("/")) + 1, resource.length());

        return qId;
    }
}
