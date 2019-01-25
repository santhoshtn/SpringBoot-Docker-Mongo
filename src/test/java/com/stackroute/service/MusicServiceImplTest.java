package com.stackroute.service;

import com.stackroute.Exceptions.TrackAlreadyFoundException;
import com.stackroute.Exceptions.TrackNotFoundException;
import com.stackroute.domain.Music;
import com.stackroute.repository.MusicRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MusicServiceImplTest {

    private Music music;
    private Optional<Music> music1;

    @Mock
    private MusicRepository musicRepository;

    @InjectMocks
    private MusicServiceImpl musicService;
    List<Music> list=null;

    @Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        music =new Music();
        music.setTrackId(1);
        music.setTrackComment("Awesome!");
        music.setTrackName("Let Her go!");
        list=new ArrayList<>();
        list.add(music);
    }

    @After
    public void tearDown() {
    music=null;
    list=null;
    }


    @Test
    public void saveMusicSuccess() throws TrackAlreadyFoundException {
        when(musicRepository.save((Music)any())).thenReturn(music);
        Music savedMusic =musicService.saveMusic(music);
        Assert.assertEquals(music,savedMusic);

        verify(musicRepository,times(1)).save(music);
    }

    @Test
    public void getAllMusicSuccess() {
        when(musicRepository.findAll()).thenReturn(list);
        List<Music> musicList=musicService.getAllMusic();
        Assert.assertEquals(list,musicList);
        verify(musicRepository,times(1)).findAll();
    }

    @Test
    public void updateMusicSuccess() throws TrackNotFoundException {
        when(musicRepository.existsById(anyInt())).thenReturn(true);
        when(musicRepository.findById(anyInt())).thenReturn(Optional.of(music));
        when(musicRepository.save(music)).thenReturn(music);
     //   Music updatedMusic=musicService.updateMusic("Awesome",1);
        Music updatedMusic=musicService.updateMusic(anyString(),1);
        Assert.assertEquals(music,updatedMusic);


    }

    @Test
    public void deleteMusicSuccess() throws TrackNotFoundException{
        when(musicRepository.existsById(anyInt())).thenReturn(true);
        when(musicRepository.findAll()).thenReturn(list);
        List<Music> musicList=musicService.deleteMusic(1);
        Assert.assertEquals(list,musicList);

        verify(musicRepository,times(1)).deleteById(1);
        verify(musicRepository,times(1)).findAll();

    }

    @Test
    public void findByIdSuccess() throws TrackNotFoundException {
        when(musicRepository.existsById(anyInt())).thenReturn(true);
        when(musicRepository.findById(anyInt())).thenReturn(Optional.of(music));
        Optional<Music> updatedMusic=musicService.findById(1);
        assertEquals(updatedMusic,Optional.of(music));


    }

    @Test
    public void findByTrackName() {
        when(musicRepository.findByTrackName(anyString())).thenReturn(list);
        List<Music> musicList=musicService.findByTrackName(anyString());
        Assert.assertEquals(list,musicList);
    }
}