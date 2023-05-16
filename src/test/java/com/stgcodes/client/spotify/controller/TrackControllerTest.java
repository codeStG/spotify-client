package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.dto.AlbumDto;
import com.stgcodes.client.spotify.dto.ArtistDto;
import com.stgcodes.client.spotify.dto.TrackDto;
import com.stgcodes.client.spotify.service.TrackService;
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
        TrackController.class,
        TrackService.class
})
class TrackControllerTest {

    @Autowired
    private TrackController trackController;

    @MockBean
    private TrackService serviceMock;

    private WebTestClient webTestClient;

    private TrackDto testTrackDto;

    private List<TrackDto> testTrackDtoList;

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient.bindToController(trackController).build();
        testTrackDto = TrackDto.builder()
                .id("0")
                .name("Some Name")
                .popularity(99)
                .discNumber(1)
                .trackNumber(1)
                .album(AlbumDto.builder().build())
                .artists(List.of(ArtistDto.builder().build()))
                .build();

        testTrackDtoList = List.of(testTrackDto, TrackDto.builder().build());
    }

    @Test
    void findById() {
        String testId = "0";
        when(serviceMock.findById(testId)).thenReturn(Mono.just(testTrackDto));

        webTestClient
                .get()
                .uri("/track/" + testId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(TrackDto.class)
                .isEqualTo(testTrackDto);

        verify(serviceMock).findById(testId);
    }

    @Test
    void findAll() {
        when(serviceMock.findAll()).thenReturn(Flux.fromIterable(testTrackDtoList));

        webTestClient
                .get()
                .uri("/track")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(TrackDto.class)
                .isEqualTo(testTrackDtoList);

        verify(serviceMock).findAll();
    }

    @Test
    void findTop() {
        when(serviceMock.findTopTen()).thenReturn(Flux.fromIterable(testTrackDtoList));

        webTestClient
                .get()
                .uri("/track/top")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(TrackDto.class)
                .isEqualTo(testTrackDtoList);

        verify(serviceMock).findTopTen();
    }
}