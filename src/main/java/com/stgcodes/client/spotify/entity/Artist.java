package com.stgcodes.client.spotify.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Artist {

    @Id
    private String id;

    private String name;

    private int popularity;

    private String[] genres;

    private Object externalUrls;

    private Object followers;

    private String href;

    private Object[] images;

    private String type;

    private String uri;
}
