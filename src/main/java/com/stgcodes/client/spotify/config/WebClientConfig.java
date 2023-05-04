package com.stgcodes.client.spotify.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class WebClientConfig {

    @Value("${spotify.uri.base}")
    private String baseUrl;

    @Bean
    WebClient webClient(ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2Client = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);

        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(oauth2Client)
                .filter(logFilter())
                .build();
    }

    private ExchangeFilterFunction logFilter() {
        return (clientRequest, next) -> {
            log.info("***********************************");
            log.info("External Request to {}", clientRequest.url());
            log.info("Method used: {}", clientRequest.method());
            log.info("***********************************");
            return next.exchange(clientRequest);
        };
    }
}
