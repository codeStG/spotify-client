package com.stgcodes.client.spotify.model.wrapper;

import com.stgcodes.client.spotify.model.Artist;
import lombok.Data;

import java.util.List;

@Data
public class ArtistsWrapper {
    private List<Artist> artists;
}
