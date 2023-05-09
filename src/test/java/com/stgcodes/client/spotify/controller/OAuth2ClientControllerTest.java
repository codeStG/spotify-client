package com.stgcodes.client.spotify.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

@WebFluxTest(controllers = OAuth2ClientController.class)
class OAuth2ClientControllerTest {

    @Autowired
    ApplicationContext context;

    @MockBean
    private ReactiveOAuth2AuthorizedClientService authorizedClientServiceMock;

    @MockBean
    private OAuth2AuthorizedClient oAuth2AuthorizedClient;

    private WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient.bindToApplicationContext(context)
                .apply(springSecurity())
                .configureClient()
                .build();
    }

    @Test
    @WithMockUser
    void index() {
        when(authorizedClientServiceMock.loadAuthorizedClient(anyString(), anyString()))
                .thenReturn(Mono.just(oAuth2AuthorizedClient));

        when(oAuth2AuthorizedClient.getAccessToken()).thenReturn(mock(OAuth2AccessToken.class));

        webTestClient.get()
                .uri("/")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .isEqualTo("index");
    }
}
