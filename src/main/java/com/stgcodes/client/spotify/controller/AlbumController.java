package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.service.AlbumService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody Mono<Album>
    findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseBody Flux<Album>
    findAll() {
        return service.findAll();
    }
}
