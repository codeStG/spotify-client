package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.model.Artist;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Service
public class AlbumService {
    private final WebClient webClient;

    public AlbumService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Album> findById(String id) {
        return webClient.get()
                .uri("/albums/{id}", id)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(Album.class);
    }
}
