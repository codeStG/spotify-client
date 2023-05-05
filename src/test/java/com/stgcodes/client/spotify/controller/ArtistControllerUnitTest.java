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
        testArtistDto = new ArtistDto();

        testArtistDto.setId("0");
        testArtistDto.setName("Some Name");
        testArtistDto.setPopularity(99);
        testArtistDto.setGenres(new String[1]);

        testArtistDtoList = List.of(testArtistDto, new ArtistDto());
    }

    @Test
    void findById() {
        String testId = "0x06iiRmDrPpU1Wlo5MHoz";
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