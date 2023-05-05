package com.stgcodes.client.spotify.controller;

import com.stgcodes.client.spotify.dto.AlbumDto;
import com.stgcodes.client.spotify.dto.ArtistDto;
import com.stgcodes.client.spotify.dto.TrackDto;
import com.stgcodes.client.spotify.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {
        AlbumController.class,
        AlbumService.class
})
class AlbumControllerUnitTest {

    @Autowired
    private AlbumController albumController;

    @MockBean
    private AlbumService serviceMock;

    private WebTestClient webTestClient;

    private AlbumDto testAlbumDto;

    private List<AlbumDto> testAlbumDtoList;

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient.bindToController(albumController).build();
        testAlbumDto = new AlbumDto();

        testAlbumDto.setId("0");
        testAlbumDto.setName("Some Name");
        testAlbumDto.setTotalTracks(0);
        testAlbumDto.setPopularity(99);
        testAlbumDto.setReleaseDate(LocalDate.now());
        testAlbumDto.setArtists(List.of(new ArtistDto()));
        testAlbumDto.setTracks(List.of(new TrackDto()));

        testAlbumDtoList = List.of(testAlbumDto, new AlbumDto());
    }

    @Test
    void findById() {
        String testId = "0x06iiRmDrPpU1Wlo5MHoz";
        when(serviceMock.findById(testId)).thenReturn(Mono.just(testAlbumDto));

        webTestClient
                .get()
                .uri("/album/" + testId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(AlbumDto.class)
                .isEqualTo(testAlbumDto);

        verify(serviceMock).findById(testId);
    }

    @Test
    void findAll() {
        when(serviceMock.findAll()).thenReturn(Flux.fromIterable(testAlbumDtoList));

        webTestClient
                .get()
                .uri("/album")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(AlbumDto.class)
                .isEqualTo(testAlbumDtoList);

        verify(serviceMock).findAll();

    }
}