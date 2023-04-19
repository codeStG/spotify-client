package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.model.wrapper.AlbumsWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AlbumService extends GenericService<Album, AlbumsWrapper> {

    public AlbumService(WebClient webClient) {
        super(webClient, Album.class, AlbumsWrapper.class);
    }

    public Mono<Album> findById(String id) {
        return requestSingleValue("/albums/" + id);
    }

    public Mono<List<Album>> findAll(String ids) {
        return requestMultipleValues("/albums?ids=" + ids)
                .map(AlbumsWrapper::getAlbums);
    }
}
