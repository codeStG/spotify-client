package com.stgcodes.client.spotify.model;

import lombok.Data;

import java.util.List;

@Data
public class Artist {
    private String id;
    private String name;
    private int popularity;
    private List<String> genres;
}
