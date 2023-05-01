package com.stgcodes.client.spotify.mapper;

import com.stgcodes.client.spotify.entity.TrackEntity;
import com.stgcodes.client.spotify.model.Track;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface TrackMapper extends Converter<TrackEntity, Track> {
    Track convert(TrackEntity trackEntity);
}
