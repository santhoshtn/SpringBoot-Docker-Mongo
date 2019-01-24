package com.stackroute.repository;

import com.stackroute.domain.Music;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends MongoRepository<Music,Integer> {

    //this is used to select trackByName
   // @Query(value = "Select m from Music m  where m.trackName = ?1")
    public List<Music> findByTrackName(String trackName);
}
