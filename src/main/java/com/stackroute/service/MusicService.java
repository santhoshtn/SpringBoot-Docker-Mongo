package com.stackroute.service;

import com.stackroute.Exceptions.TrackAlreadyFoundException;
import com.stackroute.Exceptions.TrackNotFoundException;
import com.stackroute.domain.Music;

import java.util.List;
import java.util.Optional;

public interface MusicService {

    public Music saveMusic(Music music) throws TrackAlreadyFoundException;

    public List<Music> getAllMusic();

    public Music updateMusic(String comment,int trackId) throws TrackNotFoundException;

    public List<Music> deleteMusic(int trackId) throws TrackNotFoundException;

    public Optional<Music> findById(int trackId) throws TrackNotFoundException;

    public List<Music> findByTrackName(String trackName);


}
