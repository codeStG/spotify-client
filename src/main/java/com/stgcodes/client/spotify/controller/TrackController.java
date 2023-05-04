package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.dto.TrackDto;
import com.stgcodes.client.spotify.service.TrackService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/track")
public class TrackController {

    private final TrackService service;

    public TrackController(TrackService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody Mono<TrackDto>
    findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseBody Flux<TrackDto>
    findAll() {
        return service.findAll();
    }

    @GetMapping("/top")
    @ResponseBody Flux<TrackDto>
    findTopTen() {
        return service.findTopTen();
    }
}
