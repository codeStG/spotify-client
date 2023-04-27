package com.stgcodes.client.spotify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stgcodes.client.spotify.deserializer.CustomDateDeserializer;
import com.stgcodes.client.spotify.model.wrapper.TracksWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "albums")
public class AlbumEntity {

    @Id
    private String id;

    private String name;

    @Field("album_type")
    @JsonProperty("album_type")
    private String albumType;

    @Field("total_tracks")
    @JsonProperty("total_tracks")
    private int totalTracks;

    @Field("available_markets")
    @JsonProperty("available_markets")
    private String[] availableMarkets;

    @Field("external_urls")
    @JsonProperty("external_urls")
    private Object externalUrls;

    private String href;

    private Object[] images;

    @Field("release_date")
    @JsonProperty("release_date")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate releaseDate;

    @Field("release_date_precision")
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;

    private String type;

    private String uri;

    private Object[] copyrights;

    @Field("external_ids")
    @JsonProperty("external_ids")
    private Object externalsIds;

    private String[] genres;

    private String label;

    private int popularity;

    private List<ArtistEntity> artists;

    private List<TrackEntity> tracks;

    @JsonProperty("tracks")
    private void unpackTracks(TracksWrapper tracksWrapper) {
        this.tracks = tracksWrapper.getTracks();
    }
}
