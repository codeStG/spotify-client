package com.stgcodes.client.spotify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "artists")
public class ArtistEntity {

    @Id
    private String id;

    private String[] genres;

    @Field("external_urls")
    @JsonProperty("external_urls")
    private Object externalUrls;

    private Object followers;

    private String href;

    private Object[] images;

    private String name;

    private int popularity;

    private String type;

    private String uri;
}
