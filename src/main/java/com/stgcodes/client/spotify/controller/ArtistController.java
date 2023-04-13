package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.service.ArtistService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService service;

    public ArtistController(ArtistService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody Mono<Artist>
    findById(@RequestParam(value = "id", defaultValue = "") String id) {
        return service.findById(id);
    }

    @GetMapping(params = "ids")
    @ResponseBody Mono<List<Artist>>
    findAll(@RequestParam(value = "ids", defaultValue = "") String ids) {
        return service.findAll(ids);
    }
}
