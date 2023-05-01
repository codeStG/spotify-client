package com.stgcodes.client.spotify.mapper.simple;

import com.stgcodes.client.spotify.entity.ArtistEntity;
import com.stgcodes.client.spotify.model.simple.SimpleArtist;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface SimpleArtistMapper extends Converter<ArtistEntity, SimpleArtist> {
    SimpleArtist convert(ArtistEntity artistEntity);
}
