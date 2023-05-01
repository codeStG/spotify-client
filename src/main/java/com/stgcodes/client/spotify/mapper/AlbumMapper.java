package com.stgcodes.client.spotify.mapper;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import com.stgcodes.client.spotify.model.Album;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface AlbumMapper extends Converter<AlbumEntity, Album> {
    Album convert(AlbumEntity albumEntity);
}
