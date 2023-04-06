package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.model.Artist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Service
@Slf4j
public class ArtistService {
    private final WebClient webClient;

    public ArtistService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> findById(String id) {
        log.info(String.valueOf(clientRegistrationId("spotify")));
        return webClient.get()
                .uri("/artists/{id}", id)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(String.class);
    }
}
