package com.stgcodes.client.spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Album {
    private String id;

    private String name;

    private int totalTracks;

    @JsonIgnoreProperties(value = { "popularity", "total_followers", "genres" })
    private List<Artist> artists;
}
