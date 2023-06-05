package com.stgcodes.client.spotify.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackDto {

    private String id;

    private String name;

    private int popularity;

    private int discNumber;

    private int trackNumber;

    @JsonIgnoreProperties({"total_tracks", "popularity", "release_date", "artists", "tracks"})
    private AlbumDto album;

    @JsonIgnoreProperties({"popularity", "genres"})
    private List<ArtistDto> artists;
}
