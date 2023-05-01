package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.entity.ArtistEntity;
import com.stgcodes.client.spotify.entity.TrackEntity;
import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.model.Track;
import com.stgcodes.client.spotify.model.simple.SimpleAlbum;
import com.stgcodes.client.spotify.model.simple.SimpleArtist;
import com.stgcodes.client.spotify.model.simple.SimpleTrack;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

public abstract class GenericService<T> {

    private final WebClient webClient;
    private final Class<T> modelType;

    GenericService(WebClient webClient, Class<T> modelType) {
        this.webClient = webClient;
        this.modelType = modelType;
    }

    public Mono<T> requestSingleValue(String uri) {
        return webClient.get()
                .uri(uri)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(modelType);
    }
}
