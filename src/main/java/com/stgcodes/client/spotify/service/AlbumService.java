package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.model.AlbumsWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

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

    public Mono<List<Album>> findAll(String ids) {
        return webClient.get()
                .uri("/albums?ids={ids}", ids)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(AlbumsWrapper.class)
                .map(AlbumsWrapper::getAlbums);
    }
}
