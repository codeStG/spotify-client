package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.model.wrapper.AlbumsWrapper;
import com.stgcodes.client.spotify.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService extends GenericService<AlbumEntity, AlbumsWrapper> {

    private final AlbumRepository repository;

    public AlbumService(WebClient webClient, AlbumRepository repository) {
        super(webClient, AlbumEntity.class, AlbumsWrapper.class);
        this.repository = repository;
    }

    public Mono<Album> findById(String id) {
        Mono<AlbumEntity> albumEntity = repository.findById(id)
                .switchIfEmpty(requestSingleValue("/albums/" + id)
                        .flatMap(repository::save));

        return entityToDto(albumEntity);
    }

    private Mono<Album> entityToDto(Mono<AlbumEntity> albumEntity) {
        return albumEntity.map(entity -> {
            List<Artist> artists = new ArrayList<>();
            entity.getArtists().forEach(artistEntity -> artists.add(
            Artist.builder()
                    .id(artistEntity.getId())
                    .name(artistEntity.getName())
                    .popularity(artistEntity.getPopularity())
//                        .totalFollowers(null)
                    .genres(artistEntity.getGenres())
                    .build()));

            return Album.builder()
                .id(entity.getId())
                .name(entity.getName())
                .totalTracks(entity.getTotalTracks())
                .popularity(entity.getPopularity())
                .releaseDate(entity.getReleaseDate())
                .artists(artists)
//                .tracks(null)
                .build();
        });
    }

    public Mono<List<Album>> findAll(String ids) {
        return requestMultipleValues("/albums?ids=" + ids)
                .map(AlbumsWrapper::getAlbums);
    }
}
