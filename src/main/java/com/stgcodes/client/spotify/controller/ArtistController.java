package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.service.ArtistService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService service;

    public ArtistController(ArtistService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody Mono<Artist>
    findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseBody Flux<Artist>
    findAll() {
        return service.findAll();
    }
}
