package com.stgcodes.client.spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.stgcodes.client.spotify.model.simple.SimpleAlbum;
import com.stgcodes.client.spotify.model.simple.SimpleArtist;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {

    private String id;

    private String name;

    private int popularity;

    private int discNumber;

    private int trackNumber;

    private SimpleAlbum album;

    private List<SimpleArtist> artists;
}
