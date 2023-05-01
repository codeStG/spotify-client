package com.stgcodes.client.spotify.mapper;

import com.stgcodes.client.spotify.entity.ArtistEntity;
import com.stgcodes.client.spotify.model.Artist;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface ArtistMapper extends Converter<ArtistEntity, Artist> {
    Artist convert(ArtistEntity artistEntity);
}
