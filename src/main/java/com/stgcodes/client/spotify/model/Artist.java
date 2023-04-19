package com.stgcodes.client.spotify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Artist {
    private String id;
    private String name;
    private int popularity;
    private int totalFollowers;
    private List<String> genres;

    @JsonProperty("followers")
    private void unpackFollowerCount(Map<String, Integer> followers) {
        totalFollowers = followers.get("total");
    }
}
