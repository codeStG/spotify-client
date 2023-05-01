package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.mapper.ModelMapper;
import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.repository.AlbumRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlbumService extends GenericService<AlbumEntity> {

    private final AlbumRepository repository;
    private final ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    public AlbumService(WebClient webClient, AlbumRepository repository) {
        super(webClient, AlbumEntity.class);
        this.repository = repository;
    }

    public Mono<Album> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(requestSingleValue("/albums/" + id)
                        .flatMap(repository::save))
                .mapNotNull(mapper::albumEntityToAlbum);
    }

    public Flux<Album> findAll() {
        return repository.findAll()
                .map(mapper::albumEntityToAlbum);
    }
}
