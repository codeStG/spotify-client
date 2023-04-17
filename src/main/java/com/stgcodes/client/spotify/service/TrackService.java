package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.model.Track;
import com.stgcodes.client.spotify.model.TracksWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TrackService extends GenericService<Track, TracksWrapper> {

    public TrackService(WebClient webClient) {
        super(webClient, Track.class, TracksWrapper.class);
    }

    public Mono<Track> findById(String id) {
        return requestSingleValue("/tracks/" + id);
    }

    public Mono<List<Track>> findAll(String ids) {
        return requestMultipleValues("/tracks?ids=" + ids)
                .map(TracksWrapper::getTracks);
    }
}
