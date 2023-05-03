package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.dto.ArtistDto;
import com.stgcodes.client.spotify.entity.Artist;
import com.stgcodes.client.spotify.entity.wrapper.ArtistsWrapper;
import com.stgcodes.client.spotify.mapper.ModelMapper;
import com.stgcodes.client.spotify.repository.ArtistRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Service
public class ArtistService extends GenericService<Artist> {

    private final WebClient webClient;
    private final ArtistRepository repository;
    private final ModelMapper mapper = Mappers.getMapper(ModelMapper.class);

    public ArtistService(WebClient webClient, ArtistRepository repository) {
        super(webClient, Artist.class);
        this.webClient = webClient;
        this.repository = repository;
    }

    public Mono<ArtistDto> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(requestSingleValue("/artists/" + id))
                    .flatMap(repository::save)
                .map(mapper::artistEntityToArtist);
    }

    public Flux<ArtistDto> findAll() {
        return repository.findAll()
                .map(mapper::artistEntityToArtist);
    }

    public Flux<ArtistDto> findTopTen() {
        return webClient.get()
                .uri("/me/top/artists?limit=10")
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToFlux(ArtistsWrapper.class)
                .map(ArtistsWrapper::getArtists)
                .flatMap(repository::saveAll)
                .map(mapper::artistEntityToArtist);
    }
}
