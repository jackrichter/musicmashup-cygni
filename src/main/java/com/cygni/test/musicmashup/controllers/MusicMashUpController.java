package com.cygni.test.musicmashup.controllers;

import com.cygni.test.musicmashup.services.MusicMashupService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
@AllArgsConstructor
public class MusicMashUpController {

    private MusicMashupService service;

    @GetMapping("/test")
    public void testWebClient() {
        service.testWebClient();
    }
}
