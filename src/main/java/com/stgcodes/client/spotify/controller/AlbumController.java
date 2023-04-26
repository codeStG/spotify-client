package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.service.AlbumService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService service;

    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody Mono<AlbumEntity>
    findById(@RequestParam(value = "id", defaultValue = "") String id) {
        return service.findById(id);
    }

    @GetMapping(params = "ids")
    @ResponseBody Mono<List<Album>>
    findAll(@RequestParam(value = "ids", defaultValue = "") String ids) {
        return service.findAll(ids);
    }
}
