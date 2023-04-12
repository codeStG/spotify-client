package com.stgcodes.client.spotify.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stgcodes.client.spotify.model.Artist;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ArtistService extends BaseService<Artist> {
    public ArtistService(WebClient webClient, ObjectMapper mapper) {
        super(webClient, mapper, Artist.class);
    }
}
