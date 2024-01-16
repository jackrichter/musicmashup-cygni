package com.cygni.test.musicmashup;

import com.cygni.test.musicmashup.models.response.DetailedResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MusicmashupApplication {

	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public DetailedResponse getDetailedResponse() {
		return  new DetailedResponse();
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicmashupApplication.class, args);
	}

}
