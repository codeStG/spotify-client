package com.stgcodes.client.spotify.mapper;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.entity.ArtistEntity;
import com.stgcodes.client.spotify.entity.TrackEntity;
import com.stgcodes.client.spotify.model.Album;
import com.stgcodes.client.spotify.model.Artist;
import com.stgcodes.client.spotify.model.Track;
import org.mapstruct.Mapper;

@Mapper
public interface ModelMapper {
    Album albumEntityToAlbum(AlbumEntity entity);
    Artist artistEntityToArtist(ArtistEntity entity);
    Track trackEntityToTrack(TrackEntity entity);
}
