package com.stgcodes.client.spotify.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OAuth2ClientController {

    private final ReactiveOAuth2AuthorizedClientService authorizedClientService;

    public OAuth2ClientController(ReactiveOAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/")
    public Mono<String> index(Authentication authentication) {
        return this.authorizedClientService.loadAuthorizedClient("spotify", authentication.getName())
                .map(OAuth2AuthorizedClient::getAccessToken)
                .thenReturn("index");
    }
}
