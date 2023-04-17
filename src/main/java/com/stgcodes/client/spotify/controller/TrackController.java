package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.model.Track;
import com.stgcodes.client.spotify.service.TrackService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/track")
public class TrackController {

    private final TrackService service;

    public TrackController(TrackService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    Mono<Track>
    findById(@RequestParam(value = "id", defaultValue = "") String id) {
        return service.findById(id);
    }
}
