package com.stgcodes.client.spotify.model.simple;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleAlbum {
    private String id;

    private String name;
}
