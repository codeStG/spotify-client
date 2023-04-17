package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.model.Track;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Service
public class TrackService {

    private final WebClient webClient;

    public TrackService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Track> findById(String id) {
        return webClient.get()
                .uri("/tracks/{id}", id)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(Track.class);
    }
}
