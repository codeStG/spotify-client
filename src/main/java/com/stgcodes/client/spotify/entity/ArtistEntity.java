package com.stgcodes.client.spotify.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArtistEntity {
    private String id;
    private String name;
    private int popularity;
    private int totalFollowers;
    private List<String> genres;
}
