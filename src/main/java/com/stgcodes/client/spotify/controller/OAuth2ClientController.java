package com.stgcodes.client.spotify.controller;

import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OAuth2ClientController {

    private final ReactiveClientRegistrationRepository clientRegistrationRepository;

    public OAuth2ClientController(ReactiveClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @GetMapping("/")
    public Mono<String> index() {
        return this.clientRegistrationRepository.findByRegistrationId("spotify").thenReturn("index");
    }
}
