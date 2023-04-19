package com.stgcodes.client.spotify.model.wrapper;

import com.stgcodes.client.spotify.model.Album;
import lombok.Data;

import java.util.List;

@Data
public class AlbumsWrapper {
    private List<Album> albums;
}
