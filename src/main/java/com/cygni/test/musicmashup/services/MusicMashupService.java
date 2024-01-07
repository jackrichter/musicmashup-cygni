package com.cygni.test.musicmashup.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class MusicMashupService {

    private WebClient webClient;

    public void testWebClient() {
        if (this.webClient == null) {
            System.out.println("We DON'T have a WebClient");
        } else if (this.webClient != null) {
            System.out.println("We DO have a WebClient instance");
        }
    }
}
