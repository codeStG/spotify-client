package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.model.Track;
import com.stgcodes.client.spotify.service.TrackService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/track")
public class TrackController {

    private final TrackService service;

    public TrackController(TrackService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody Mono<Track>
    findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseBody Flux<Track>
    findAll() {
        return service.findAll();
    }
}
