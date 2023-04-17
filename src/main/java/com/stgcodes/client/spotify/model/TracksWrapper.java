package com.stgcodes.client.spotify.model;

import lombok.Data;

import java.util.List;

@Data
public class TracksWrapper {
    private List<Track> tracks;
}
