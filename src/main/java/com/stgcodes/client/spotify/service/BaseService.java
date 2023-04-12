package com.stgcodes.client.spotify.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.stgcodes.client.spotify.utils.UrlBuilder;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

public abstract class BaseService<T> {

    private final WebClient webClient;
    private final Class<T> typeParameterClass;
    private final UrlBuilder urlBuilder;
    private final String jsonRoot;
    private final ObjectReader reader;

    BaseService(WebClient webClient, ObjectMapper mapper, Class<T> typeParameterClass) {
        this.webClient = webClient;
        this.typeParameterClass = typeParameterClass;
        this.urlBuilder = new UrlBuilder(typeParameterClass);
        this.jsonRoot = typeParameterClass.getSimpleName().toLowerCase() + "s";
        this.reader = mapper.readerFor(mapper.getTypeFactory().constructCollectionType(List.class, typeParameterClass));
    }

    public Mono<T> findById(String id) {
        return webClient.get()
                .uri(urlBuilder.buildUrlById(id))
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(typeParameterClass);
    }
}
