package com.stgcodes.client.spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Album {
    private String id;

    private String name;

    @JsonProperty("total_tracks")
    private int totalTracks;

    @JsonIgnoreProperties(value = { "popularity", "totalFollowers", "genres" })
    private List<Artist> artists;
}
