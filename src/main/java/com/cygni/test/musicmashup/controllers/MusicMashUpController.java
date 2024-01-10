package com.cygni.test.musicmashup.controllers;

import com.cygni.test.musicmashup.services.MusicMashupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import java.util.Objects;

@RestController
@RequestMapping("/music")
@AllArgsConstructor
public class MusicMashUpController {

    private MusicMashupService service;

    @GetMapping(value = "/musicbrainz/{mbId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMusicBrainz(@PathVariable String mbId) {
        service.getMusicBrainzInfo(mbId).subscribe();
//        System.out.println(Objects.requireNonNull(service.getMusicInfoMono().block()));
        service.getWikiDataInfo().subscribe();
//        System.out.println(Objects.requireNonNull(service.getWikiDataInfo().block()));
        service.getWikipediaInfo().subscribe();
//        System.out.println(Objects.requireNonNull(service.getWikipediaInfo().block()).getAdditionalProperties());

        return new ResponseEntity<>("This is a Responce", HttpStatus.OK);
    }
}
