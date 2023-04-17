package com.stgcodes.client.spotify.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.stgcodes.client.spotify.deserializer.CustomDateDeserializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Album {
    private String id;

    private String name;

    private int totalTracks;
    private int popularity;

    @JsonFormat(pattern = "uuuu-MM-dd")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDate releaseDate;

    @JsonIgnoreProperties(value = { "popularity", "total_followers", "genres" })
    private List<Artist> artists;
}
