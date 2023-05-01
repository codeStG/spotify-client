package com.stgcodes.client.spotify.repository;

import com.stgcodes.client.spotify.entity.ArtistEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends ReactiveMongoRepository<ArtistEntity, String> {
}
