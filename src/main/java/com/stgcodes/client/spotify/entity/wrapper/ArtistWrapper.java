package com.stgcodes.client.spotify.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stgcodes.client.spotify.entity.Artist;
import lombok.Data;

import java.util.List;

@Data
public class ArtistWrapper {

    private List<Artist> artists;

    @JsonProperty("items")
    private void unpackArtists(List<Artist> items) {
        this.artists = items;
    }
}
