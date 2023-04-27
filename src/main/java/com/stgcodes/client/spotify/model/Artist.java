package com.stgcodes.client.spotify.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Artist {
    private String id;
    private String name;
    private int popularity;
    private int totalFollowers;
    private String[] genres;

    @JsonProperty("followers")
    private void unpackFollowerCount(Map<String, Integer> followers) {
        totalFollowers = followers.get("total");
    }
}
