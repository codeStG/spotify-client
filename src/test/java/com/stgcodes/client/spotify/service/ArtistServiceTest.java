package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.dto.ArtistDto;
import com.stgcodes.client.spotify.entity.Artist;
import com.stgcodes.client.spotify.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock
    private ArtistRepository repositoryMock;

    @InjectMocks
    private ArtistService service;

    private Artist testArtist;
    private Artist testArtist2;
    private ArtistDto testArtistDto;
    private ArtistDto testArtistDto2;

    @BeforeEach
    void setUp() {
        testArtist = Artist.builder().id("0").build();
        testArtist2 = Artist.builder().id("1").build();
        testArtistDto = ArtistDto.builder().id("0").build();
        testArtistDto2 = ArtistDto.builder().id("1").build();
    }

    @Test
    void findById() {
        String testId = "0";

        given(repositoryMock.findById(testId)).willReturn(Mono.just(testArtist));

        StepVerifier.create(service.findById(testId))
                .expectNext(testArtistDto)
                .verifyComplete();

        verify(repositoryMock).findById(testId);
    }

    @Test
    void findAll() {
        given(repositoryMock.findAll()).willReturn(Flux.fromIterable(List.of(testArtist, testArtist2)));

        StepVerifier.create(service.findAll())
                .expectNext(testArtistDto)
                .expectNext(testArtistDto2)
                .verifyComplete();

        verify(repositoryMock).findAll();
    }
}