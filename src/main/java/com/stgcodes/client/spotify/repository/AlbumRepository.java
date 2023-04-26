package com.stgcodes.client.spotify.repository;

import com.stgcodes.client.spotify.entity.AlbumEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends ReactiveMongoRepository<AlbumEntity, String> {
}
