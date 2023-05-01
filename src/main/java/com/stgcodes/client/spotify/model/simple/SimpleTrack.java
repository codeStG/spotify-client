package com.stgcodes.client.spotify.model.simple;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SimpleTrack {
    private String id;
    private String name;
    private List<SimpleArtist> artists;
}
