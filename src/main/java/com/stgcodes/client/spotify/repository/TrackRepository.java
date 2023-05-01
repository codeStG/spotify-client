package com.stgcodes.client.spotify.repository;

import com.stgcodes.client.spotify.entity.TrackEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends ReactiveMongoRepository<TrackEntity, String> {
}
