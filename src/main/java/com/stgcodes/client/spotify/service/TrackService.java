package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.entity.TrackEntity;
import com.stgcodes.client.spotify.model.Track;
import com.stgcodes.client.spotify.model.wrapper.TracksWrapper;
import com.stgcodes.client.spotify.repository.TrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TrackService extends GenericService<TrackEntity, TracksWrapper> {

    private final TrackRepository repository;

    public TrackService(WebClient webClient, TrackRepository repository) {
        super(webClient, TrackEntity.class, TracksWrapper.class);
        this.repository = repository;
    }

    public Mono<Track> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(requestSingleValue("/tracks/" + id)
                    .flatMap(repository::save))
                .map(this::entityToModel);
    }

    public Flux<Track> findAll() {
        return repository.findAll()
                .map(this::entityToModel);
    }
}
