package com.stgcodes.client.spotify.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class Album {
    private String id;

    private String name;

    private int totalTracks;

    private int popularity;

    @JsonFormat(pattern = "uuuu-MM-dd")
    private LocalDate releaseDate;

    @JsonIgnoreProperties({"popularity", "genres"})
    private List<Artist> artists;

    @JsonIgnoreProperties(value = { "album", "popularity", "disc_number", "track_number", "artists" })
    private List<Track> tracks;
}
