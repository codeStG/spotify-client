package com.stgcodes.client.spotify.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Artist {
    private String id;
    private String name;
    private int popularity;
    private String[] genres;
}
