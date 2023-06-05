package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.dto.AlbumDto;
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

    @GetMapping({"/{id}", "/{id}/"})
    @ResponseBody Mono<AlbumDto>
    findById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping({"", "/"})
    @ResponseBody Flux<AlbumDto>
    findAll() {
        return service.findAll();
    }
}
