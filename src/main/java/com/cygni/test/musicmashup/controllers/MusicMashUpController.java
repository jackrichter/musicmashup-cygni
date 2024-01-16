package com.cygni.test.musicmashup.controllers;

import com.cygni.test.musicmashup.models.response.DetailedResponse;
import com.cygni.test.musicmashup.services.MusicMashupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
@AllArgsConstructor
@SuppressWarnings("ALL")
public class MusicMashUpController {

    private MusicMashupService service;

    @GetMapping(value = "/musicbrainz/{mbId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailedResponse> getMusicBrainz(@PathVariable String mbId) {

        // Services called asynchronously, non-blocking
        service.getMusicBrainzInfo(mbId).subscribe();
        service.getWikiDataInfo().subscribe();
        service.getWikipediaInfo().subscribe();

        // Service called synchronously
        service.getCoverArtInfo();

        // Build the response object
        DetailedResponse response = service.getResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
