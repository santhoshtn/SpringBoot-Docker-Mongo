package com.stackroute.controller;

import com.stackroute.Exceptions.TrackAlreadyFoundException;
import com.stackroute.Exceptions.TrackNotFoundException;
import com.stackroute.domain.Music;
import com.stackroute.service.MusicService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/")
@Api(value="music application",description = "you can add your music search and delete")
public class MusicController {

    private MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService)
    {
        this.musicService=musicService;
    }

    //this method is used to post data for the first time
    @PostMapping("music")
    public ResponseEntity<?> saveMusic(@RequestBody Music music) throws TrackAlreadyFoundException
    {
        ResponseEntity responseEntity;

            musicService.saveMusic(music);
            responseEntity=new ResponseEntity<String>("successfully created", HttpStatus.CREATED);

        return responseEntity;
    }

    //this is a get method and it is used to get all the values
    @GetMapping("musics")
    public ResponseEntity<?> getAllMusic()
    {
        return new ResponseEntity<List<Music>>(musicService.getAllMusic(),HttpStatus.OK);
    }

    //this is used to update comments
    @PutMapping("music/{trackId}")
    public ResponseEntity<?> updateMusic(@RequestBody Music music,@PathVariable("trackId") int trackId) throws TrackAlreadyFoundException,TrackNotFoundException
    {
        ResponseEntity responseEntity;

        String comment=music.getTrackComment();
        musicService.updateMusic(comment,trackId);


           musicService.saveMusic(music);
           responseEntity=new ResponseEntity<String>("Successfully updated",HttpStatus.OK);


        return responseEntity;
    }

    //this method is used to delete by trackID
    @DeleteMapping("music/{trackId}")
    public ResponseEntity<?> deleteMusic(@PathVariable("trackId") int trackId) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;

            responseEntity= new ResponseEntity<List<Music>>(musicService.deleteMusic(trackId),HttpStatus.OK);

        return responseEntity;
    }

    //this method is used to get perticular music by giving trackid
    @GetMapping("music/{trackId}")
    public ResponseEntity<?> findById(@PathVariable("trackId")int trackId) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;

            responseEntity= new ResponseEntity<Optional<Music>>(musicService.findById(trackId),HttpStatus.OK);
        return responseEntity;

    }
    //this method is used to get perticular music by giving trackName
    @GetMapping("musics/{trackName}")
    public ResponseEntity<?> findByName(@PathVariable String trackName)
    {
        return new ResponseEntity(musicService.findByTrackName(trackName),HttpStatus.OK);
    }





}
