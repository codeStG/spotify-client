package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.model.ArtistsWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Service
public class ArtistService {
    private final WebClient webClient;

    public ArtistService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Artist> findById(String id) {
        return webClient.get()
                .uri("/artists/{id}", id)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(Artist.class);
    }

    public Mono<List<Artist>> findAll(String ids) {
        return webClient.get()
                .uri("/artists?ids={ids}", ids)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(ArtistsWrapper.class)
                .map(ArtistsWrapper::getArtists);
    }
}
