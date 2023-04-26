package com.stgcodes.client.spotify.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "albums")
public class AlbumEntity {

    @Id
    private String id;

    private String name;

    private int totalTracks;

    private int popularity;

    @JsonFormat(pattern = "uuuu-MM-dd")
    private LocalDate releaseDate;
}
