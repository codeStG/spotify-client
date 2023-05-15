package com.stgcodes.client.spotify.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto {
    private String id;

    private String name;

    private int totalTracks;

    private int popularity;

    @JsonFormat(pattern = "uuuu-MM-dd")
    private LocalDate releaseDate;

    @JsonIgnoreProperties({"popularity", "genres"})
    private List<ArtistDto> artists;

    @JsonIgnoreProperties(value = { "album", "popularity", "disc_number", "track_number", "artists" })
    private List<TrackDto> tracks;
}
