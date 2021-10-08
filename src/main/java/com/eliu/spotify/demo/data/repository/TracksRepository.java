package com.eliu.spotify.demo.data.repository;

import com.eliu.spotify.demo.data.entity.TrackTbl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TracksRepository extends CrudRepository<TrackTbl, String> {
}
