package com.stgcodes.client.spotify.model.simple;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleArtist {
    private String id;
    private String name;
}
