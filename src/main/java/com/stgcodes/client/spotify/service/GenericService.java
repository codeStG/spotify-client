package com.stgcodes.client.spotify.service;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.entity.ArtistEntity;
import com.stgcodes.client.spotify.entity.TrackEntity;
import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.model.Track;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

//TODO: Extract entityToModel out to their respective service classes; make the method abstract
public abstract class GenericService<T> {

    private final WebClient webClient;
    private final Class<T> modelType;

    GenericService(WebClient webClient, Class<T> modelType) {
        this.webClient = webClient;
        this.modelType = modelType;
    }

    public Mono<T> requestSingleValue(String uri) {
        return webClient.get()
                .uri(uri)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .bodyToMono(modelType);
    }

    Album entityToModel(AlbumEntity albumEntity) {
        List<Artist> artists = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();

        albumEntity.getArtists().forEach(artistEntity -> artists.add(entityToModel(artistEntity)));
        albumEntity.getTracks().forEach(trackEntity -> tracks.add(entityToSimpleModel(trackEntity)));

        return Album.builder()
                .id(albumEntity.getId())
                .name(albumEntity.getName())
                .totalTracks(albumEntity.getTotalTracks())
                .popularity(albumEntity.getPopularity())
                .releaseDate(albumEntity.getReleaseDate())
                .artists(artists)
                .tracks(tracks)
                .build();
    }

    Artist entityToModel(ArtistEntity artistEntity) {
        return Artist.builder()
                .id(artistEntity.getId())
                .name(artistEntity.getName())
                .popularity(artistEntity.getPopularity())
                .genres(artistEntity.getGenres())
                .build();
    }

    Track entityToModel(TrackEntity trackEntity) {
        List<Artist> artists = new ArrayList<>();
        trackEntity.getArtists().forEach(artistEntity -> artists.add(entityToModel(artistEntity)));

        return Track.builder()
                .id(trackEntity.getId())
                .name(trackEntity.getName())
                .popularity(trackEntity.getPopularity())
                .discNumber(trackEntity.getDiscNumber())
                .trackNumber(trackEntity.getTrackNumber())
                .album(entityToSimpleModel(trackEntity.getAlbum()))
                .artists(artists)
                .build();
    }

    Album entityToSimpleModel(AlbumEntity albumEntity) {
        List<Artist> simpleArtists = new ArrayList<>();
        albumEntity.getArtists().forEach(artistEntity -> simpleArtists.add(entityToSimpleModel(artistEntity)));

        return Album.builder()
                .id(albumEntity.getId())
                .name(albumEntity.getName())
                .totalTracks(albumEntity.getTotalTracks())
                .releaseDate(albumEntity.getReleaseDate())
                .artists(simpleArtists)
                .build();
    }

    Artist entityToSimpleModel(ArtistEntity artistEntity) {
        return Artist.builder()
                .id(artistEntity.getId())
                .name(artistEntity.getName())
                .build();
    }

    //TODO: Spotify API will return up to 50 tracks in an Album
    // by default - Need to add logic to check "total" field on entity
    // and compare to "limit" field on entity - if "total" is greater
    // than "limit", we must gather the rest of the Tracks
    Track entityToSimpleModel(TrackEntity trackEntity) {
        List<Artist> simpleArtists = new ArrayList<>();
        trackEntity.getArtists().forEach(artistEntity -> simpleArtists.add(entityToSimpleModel(artistEntity)));

        return Track.builder()
                .id(trackEntity.getId())
                .name(trackEntity.getName())
                .discNumber(trackEntity.getDiscNumber())
                .trackNumber(trackEntity.getTrackNumber())
                .artists(simpleArtists)
                .build();
    }
}
