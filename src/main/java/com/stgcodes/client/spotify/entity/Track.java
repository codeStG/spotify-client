package com.stgcodes.client.spotify.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Track {

    @Id
    private String id;

    private String name;

    private Album album;

    private List<Artist> artists;

    private int popularity;

    private int discNumber;

    private int trackNumber;

    private String[] availableMarkets;

    private int durationMs;

    private boolean explicit;

    private Object externalIds;

    private Object externalUrls;

    private String href;

    private boolean isPlayable;

    private Object linkedFrom;

    private Object restrictions;

    private String previewUrl;

    private String type;

    private String uri;

    private boolean isLocal;
}
