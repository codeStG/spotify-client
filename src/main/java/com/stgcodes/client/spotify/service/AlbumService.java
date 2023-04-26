package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.model.wrapper.AlbumsWrapper;
import com.stgcodes.client.spotify.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AlbumService extends GenericService<Album, AlbumsWrapper> {

    private final AlbumRepository repository;

    public AlbumService(WebClient webClient, AlbumRepository repository) {
        super(webClient, Album.class, AlbumsWrapper.class);
        this.repository = repository;
    }

    public Mono<AlbumEntity> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(requestSingleValue("/albums/" + id)
                        .map(album -> AlbumEntity.builder()
                                .id(album.getId())
                                .name(album.getName())
                                .totalTracks(album.getTotalTracks())
                                .popularity(album.getPopularity())
                                .releaseDate(album.getReleaseDate())
                                .build()))
                .flatMap(repository::save);
    }

    public Mono<List<Album>> findAll(String ids) {
        return requestMultipleValues("/albums?ids=" + ids)
                .map(AlbumsWrapper::getAlbums);
    }
}
