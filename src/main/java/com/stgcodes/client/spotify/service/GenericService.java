package com.stgcodes.client.spotify.service;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

public abstract class GenericService<T> {

    private final WebClient webClient;
    private final Class<T> modelType;

    GenericService(WebClient webClient, Class<T> modelType) {
        this.webClient = webClient;
        this.modelType = modelType;
    }

    public Mono<T> requestSingleValue(String uri) {
        return webClient.get()
                .uri(uri)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(modelType);
    }
}
