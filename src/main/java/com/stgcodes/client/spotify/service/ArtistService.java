package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.entity.ArtistEntity;
import com.stgcodes.client.spotify.mapper.ModelMapper;
import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.repository.ArtistRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArtistService extends GenericService<ArtistEntity> {

    private final ArtistRepository repository;
    private final ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    public ArtistService(WebClient webClient, ArtistRepository repository) {
        super(webClient, ArtistEntity.class);
        this.repository = repository;
    }

    public Mono<Artist> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(requestSingleValue("/artists/" + id))
                    .flatMap(repository::save)
                .map(mapper::artistEntityToArtist);
    }

    public Flux<Artist> findAll() {
        return repository.findAll()
                .map(mapper::artistEntityToArtist);
    }
}
