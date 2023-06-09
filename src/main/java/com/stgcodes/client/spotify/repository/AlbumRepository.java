package com.stgcodes.client.spotify.repository;

import com.stgcodes.client.spotify.entity.Album;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends ReactiveMongoRepository<Album, String> {
}
