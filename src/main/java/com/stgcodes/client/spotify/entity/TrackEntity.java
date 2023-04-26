package com.stgcodes.client.spotify.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrackEntity {
    private String id;

    private String name;

    private int popularity;

    private int discNumber;

    private int trackNumber;

    private AlbumEntity album;
}
