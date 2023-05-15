package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.dto.AlbumDto;
import com.stgcodes.client.spotify.entity.Album;
import com.stgcodes.client.spotify.repository.AlbumRepository;
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
class AlbumServiceTest {

    @Mock
    private AlbumRepository repositoryMock;

    @InjectMocks
    private AlbumService service;

    private Album testAlbum;
    private Album testAlbum2;
    private AlbumDto testAlbumDto;
    private AlbumDto testAlbumDto2;

    @BeforeEach
    void setUp() {
        testAlbum = Album.builder().id("0").build();
        testAlbum2 = Album.builder().id("1").build();
        testAlbumDto = AlbumDto.builder().id("0").build();
        testAlbumDto2 = AlbumDto.builder().id("1").build();
    }

    @Test
    void findById() {
        String testId = "0";

        given(repositoryMock.findById(testId)).willReturn(Mono.just(testAlbum));

        StepVerifier.create(service.findById(testId))
                        .expectNext(testAlbumDto)
                                .verifyComplete();

        verify(repositoryMock).findById(testId);
    }

    @Test
    void findAll() {
        given(repositoryMock.findAll()).willReturn(Flux.fromIterable(List.of(testAlbum, testAlbum2)));

        StepVerifier.create(service.findAll())
                .expectNext(testAlbumDto)
                .expectNext(testAlbumDto2)
                .verifyComplete();

        verify(repositoryMock).findAll();
    }
}