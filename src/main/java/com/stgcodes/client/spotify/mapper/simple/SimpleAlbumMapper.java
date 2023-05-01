package com.stgcodes.client.spotify.mapper.simple;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.model.simple.SimpleAlbum;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface SimpleAlbumMapper extends Converter<AlbumEntity, SimpleAlbum> {
    SimpleAlbum convert(AlbumEntity albumEntity);
}
