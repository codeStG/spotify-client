package com.stgcodes.client.spotify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tracks")
public class TrackEntity {

    @Id
    private String id;

    private String name;

    @JsonIgnoreProperties("tracks")
    private AlbumEntity album;

    private List<ArtistEntity> artists;

    private int popularity;

    @Field("disc_number")
    @JsonProperty("disc_number")
    private int discNumber;

    @Field("track_number")
    @JsonProperty("track_number")
    private int trackNumber;

    @Field("available_markets")
    @JsonProperty("available_markets")
    private String[] availableMarkets;

    @Field("duration_ms")
    @JsonProperty("duration_ms")
    private int durationMs;

    private boolean explicit;

    @Field("external_ids")
    @JsonProperty("external_ids")
    private Object externalIds;

    @Field("external_urls")
    @JsonProperty("external_urls")
    private Object externalUrls;

    private String href;

    @Field("is_playable")
    @JsonProperty("is_playable")
    private boolean isPlayable;

    @Field("linked_from")
    @JsonProperty("linked_from")
    private Object linkedFrom;

    private Object restrictions;

    @Field("preview_url")
    @JsonProperty("preview_url")
    private String previewUrl;

    private String type;

    private String uri;

    @Field("is_local")
    @JsonProperty("is_local")
    private boolean isLocal;
}
