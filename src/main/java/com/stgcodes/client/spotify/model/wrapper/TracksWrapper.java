package com.stgcodes.client.spotify.model.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stgcodes.client.spotify.model.Track;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true)
public class TracksWrapper {

    private List<Track> tracks;

    @JsonProperty("items")
    private void unpackItems(List<Track> items) {
        this.tracks = items;
    }
}
