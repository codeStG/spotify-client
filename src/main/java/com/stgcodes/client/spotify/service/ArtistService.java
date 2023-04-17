package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.model.ArtistsWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ArtistService extends GenericService<Artist, ArtistsWrapper> {

    public ArtistService(WebClient webClient) {
        super(webClient, Artist.class, ArtistsWrapper.class);
    }

    public Mono<Artist> findById(String id) {
        return requestSingleValue("/artists/" + id);
    }

    public Mono<List<Artist>> findAll(String ids) {
        return requestMultipleValues("/artists?ids=" + ids)
                .map(ArtistsWrapper::getArtists);
    }
}
