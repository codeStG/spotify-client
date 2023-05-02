package com.stgcodes.client.spotify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.stgcodes.client.spotify.deserializer.CustomDateDeserializer;
import com.stgcodes.client.spotify.entity.wrapper.TracksWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "album")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AlbumEntity {

    @Id
    private String id;

    private String name;

    private String albumType;

    private int totalTracks;

    private String[] availableMarkets;

    private Object externalUrls;

    private String href;

    private Object[] images;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate releaseDate;

    private String releaseDatePrecision;

    private Object restrictions;

    private String type;

    private String uri;

    private Object[] copyrights;

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
