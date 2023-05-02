package com.stgcodes.client.spotify.repository;

import com.stgcodes.client.spotify.entity.Artist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends ReactiveMongoRepository<Artist, String> {
}
