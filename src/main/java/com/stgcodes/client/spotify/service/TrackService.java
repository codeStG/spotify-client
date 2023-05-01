package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.entity.TrackEntity;
import com.stgcodes.client.spotify.mapper.ModelMapper;
import com.stgcodes.client.spotify.model.Track;
import com.stgcodes.client.spotify.repository.TrackRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TrackService extends GenericService<TrackEntity> {

    private final TrackRepository repository;
    private final ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    public TrackService(WebClient webClient, TrackRepository repository) {
        super(webClient, TrackEntity.class);
        this.repository = repository;
    }

    public Mono<Track> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(requestSingleValue("/tracks/" + id)
                    .flatMap(repository::save))
                .map(mapper::trackEntityToTrack);
    }

    public Flux<Track> findAll() {
        return repository.findAll()
                .map(mapper::trackEntityToTrack);
    }
}
