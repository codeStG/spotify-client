package com.stgcodes.client.spotify.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ArtistDto {
    private String id;
    private String name;
    private int popularity;
    private String[] genres;
}
