package com.stgcodes.client.spotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {

    private String id;

    private String name;

    private int popularity;

    private int discNumber;

    private int trackNumber;

    @JsonIgnoreProperties({"total_tracks", "popularity", "release_date", "artists", "tracks"})
    private Album album;

    @JsonIgnoreProperties({"popularity", "genres"})
    private List<Artist> artists;
}
