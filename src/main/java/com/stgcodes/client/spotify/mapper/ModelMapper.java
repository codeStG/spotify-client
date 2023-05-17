package com.stgcodes.client.spotify.mapper;

import com.stgcodes.client.spotify.dto.AlbumDto;
import com.stgcodes.client.spotify.dto.ArtistDto;
import com.stgcodes.client.spotify.dto.TrackDto;
import com.stgcodes.client.spotify.entity.Album;
import com.stgcodes.client.spotify.entity.Artist;
import com.stgcodes.client.spotify.entity.Track;
import org.mapstruct.Mapper;

@Mapper
public interface ModelMapper {
    AlbumDto albumEntityToAlbum(Album entity);
    ArtistDto artistEntityToArtist(Artist entity);
    TrackDto trackEntityToTrack(Track entity);
}
