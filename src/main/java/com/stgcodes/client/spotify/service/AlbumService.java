package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.model.Track;
import com.stgcodes.client.spotify.model.wrapper.AlbumsWrapper;
import com.stgcodes.client.spotify.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService extends GenericService<AlbumEntity, AlbumsWrapper> {

    private final AlbumRepository repository;

    public AlbumService(WebClient webClient, AlbumRepository repository) {
        super(webClient, AlbumEntity.class, AlbumsWrapper.class);
        this.repository = repository;
    }

    public Mono<Album> findById(String id) {
        Mono<AlbumEntity> albumEntity = repository.findById(id)
                .switchIfEmpty(requestSingleValue("/albums/" + id)
                        .flatMap(repository::save));

        return entityToModel(albumEntity);
    }

    public Mono<List<Album>> findAll(String ids) {
        return requestMultipleValues("/albums?ids=" + ids)
                .map(AlbumsWrapper::getAlbums);
    }
}
