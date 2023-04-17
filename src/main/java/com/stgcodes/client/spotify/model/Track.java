package com.stgcodes.client.spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Track {

    private String id;

    private String name;

    private int popularity;

    private int discNumber;

    private int trackNumber;

    @JsonIgnoreProperties(value = { "popularity", "artists" })
    private Album album;

    @JsonIgnoreProperties(value = { "popularity", "total_followers", "genres" })
    private List<Artist> artists;
}
