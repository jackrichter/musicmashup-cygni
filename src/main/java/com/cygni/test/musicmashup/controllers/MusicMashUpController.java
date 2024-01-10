package com.cygni.test.musicmashup.controllers;

import com.cygni.test.musicmashup.models.wikipedia.Page;
import com.cygni.test.musicmashup.services.MusicMashupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
@AllArgsConstructor
public class MusicMashUpController {

    private MusicMashupService service;

    @GetMapping("/musicbrainz/{mbId}")
    public void getMusicBrainz(@PathVariable String mbId) {
        service.getMusicBrainzInfo(mbId).subscribe();
//        System.out.println(service.getMusicInfoMono().block());
        service.getWikiDataInfo().subscribe();
//        System.out.println(service.getWikiDataInfo().block());
        service.getWikipediaInfo().subscribe();

        Page wikiPage = service.getWikipediaInfo().block();
        System.out.println(wikiPage.getAdditionalProperties());
    }
}
