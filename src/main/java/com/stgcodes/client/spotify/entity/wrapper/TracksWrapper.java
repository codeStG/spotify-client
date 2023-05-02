package com.stgcodes.client.spotify.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stgcodes.client.spotify.entity.TrackEntity;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, allowSetters = true)
public class TracksWrapper {

    private List<TrackEntity> tracks;

    @JsonProperty("items")
    private void unpackItems(List<TrackEntity> items) {
        this.tracks = items;
    }
}
