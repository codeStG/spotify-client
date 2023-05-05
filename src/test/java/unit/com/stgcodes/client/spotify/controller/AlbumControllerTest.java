package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.dto.AlbumDto;
import com.stgcodes.client.spotify.dto.ArtistDto;
import com.stgcodes.client.spotify.dto.TrackDto;
import com.stgcodes.client.spotify.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = AlbumController.class)
@AutoConfigureWebTestClient
class AlbumControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AlbumService serviceMock;

    private String testId = "0x06iiRmDrPpU1Wlo5MHoz";

    private AlbumDto testAlbumDto;

    @BeforeEach
    void setup() {
        testAlbumDto = AlbumDto.builder()
                .id(testId)
                .name("Trillstatik 2")
                .totalTracks(10)
                .popularity(26)
                .releaseDate(LocalDate.now())
                .artists(List.of(ArtistDto.builder().build()))
                .tracks(List.of(TrackDto.builder().build()))
                .build();
    }

    @Test
    void findById() {
        when(serviceMock.findById(testId)).thenReturn(Mono.just(testAlbumDto));

        webTestClient.get()
                .uri("/album/{testId}", testId)
                .exchange()
                .expectStatus()
                .is3xxRedirection()
                .expectBody(AlbumDto.class)
                .isEqualTo(testAlbumDto);
    }
}