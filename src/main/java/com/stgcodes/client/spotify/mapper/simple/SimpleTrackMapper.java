package com.stgcodes.client.spotify.mapper.simple;

import com.stgcodes.client.spotify.entity.TrackEntity;
import com.stgcodes.client.spotify.model.simple.SimpleTrack;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface SimpleTrackMapper extends Converter<TrackEntity, SimpleTrack> {
    SimpleTrack convert(TrackEntity trackEntity);
}
