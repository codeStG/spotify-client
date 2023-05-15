package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.dto.AlbumDto;
import com.stgcodes.client.spotify.entity.Album;
import com.stgcodes.client.spotify.repository.AlbumRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlbumServiceUnitTest {

    @Mock
    private AlbumRepository repositoryMock;

    @InjectMocks
    private AlbumService service;

    private Album testAlbum;
    private AlbumDto testAlbumDto;

    @BeforeEach
    void setUp() {
        testAlbum = new Album();
        testAlbum.setId("0");

        testAlbumDto = new AlbumDto();
        testAlbumDto.setId("0");
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
}