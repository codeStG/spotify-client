package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.dto.AlbumDto;
import com.stgcodes.client.spotify.entity.Album;
import com.stgcodes.client.spotify.mapper.ModelMapper;
import com.stgcodes.client.spotify.repository.AlbumRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlbumService extends GenericService<Album> {

    private final AlbumRepository repository;
    private final ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    public AlbumService(WebClient webClient, AlbumRepository repository) {
        super(webClient, Album.class);
        this.repository = repository;
    }

    public Mono<AlbumDto> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(requestSingleValue("/albums/" + id)
                        .flatMap(repository::save))
                .mapNotNull(mapper::albumEntityToAlbum);
    }

    public Flux<AlbumDto> findAll() {
        return repository.findAll()
                .map(mapper::albumEntityToAlbum);
    }
}
