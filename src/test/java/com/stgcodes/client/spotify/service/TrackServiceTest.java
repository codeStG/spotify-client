package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.dto.TrackDto;
import com.stgcodes.client.spotify.entity.Track;
import com.stgcodes.client.spotify.repository.TrackRepository;
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
class TrackServiceTest {

    @Mock
    private TrackRepository repositoryMock;

    @InjectMocks
    private TrackService service;

    private Track testTrack;
    private Track testTrack2;
    private TrackDto testTrackDto;
    private TrackDto testTrackDto2;

    @BeforeEach
    void setUp() {
        testTrack = Track.builder().id("0").build();
        testTrack2 = Track.builder().id("1").build();
        testTrackDto = TrackDto.builder().id("0").build();
        testTrackDto2 = TrackDto.builder().id("1").build();
    }

    @Test
    void findById() {
        String testId = "0";

        given(repositoryMock.findById(testId)).willReturn(Mono.just(testTrack));

        StepVerifier.create(service.findById(testId))
                .expectNext(testTrackDto)
                .verifyComplete();

        verify(repositoryMock).findById(testId);
    }

    @Test
    void findAll() {
        given(repositoryMock.findAll()).willReturn(Flux.fromIterable(List.of(testTrack, testTrack2)));

        StepVerifier.create(service.findAll())
                .expectNext(testTrackDto)
                .expectNext(testTrackDto2)
                .verifyComplete();

        verify(repositoryMock).findAll();
    }
}