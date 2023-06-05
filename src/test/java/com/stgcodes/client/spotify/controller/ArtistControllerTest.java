package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.dto.ArtistDto;
import com.stgcodes.client.spotify.service.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {
        ArtistController.class,
        ArtistService.class
})
class ArtistControllerTest {

    @Autowired
    private ArtistController artistController;

    @MockBean
    private ArtistService serviceMock;

    private WebTestClient webTestClient;

    private ArtistDto testArtistDto;

    private List<ArtistDto> testArtistDtoList;

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient.bindToController(artistController).build();
        testArtistDto = ArtistDto.builder()
                .id("0")
                .name("Some Name")
                .popularity(99)
                .genres(new String[1])
                .build();

        testArtistDtoList = List.of(testArtistDto, ArtistDto.builder().build());
    }

    @Test
    void findById() {
        String testId = "0";
        when(serviceMock.findById(testId)).thenReturn(Mono.just(testArtistDto));

        webTestClient
                .get()
                .uri("/artist/" + testId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(ArtistDto.class)
                .isEqualTo(testArtistDto);

        verify(serviceMock).findById(testId);
    }

    @Test
    void findAll() {
        when(serviceMock.findAll()).thenReturn(Flux.fromIterable(testArtistDtoList));

        webTestClient
                .get()
                .uri("/artist")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(ArtistDto.class)
                .isEqualTo(testArtistDtoList);

        verify(serviceMock).findAll();
    }

    @Test
    void findTop() {
        when(serviceMock.findTopTen()).thenReturn(Flux.fromIterable(testArtistDtoList));

        webTestClient
                .get()
                .uri("/artist/top")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(ArtistDto.class)
                .isEqualTo(testArtistDtoList);

        verify(serviceMock).findTopTen();
    }
}