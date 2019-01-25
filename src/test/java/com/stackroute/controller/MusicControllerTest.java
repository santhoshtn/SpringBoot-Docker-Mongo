package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Exceptions.TrackAlreadyFoundException;
import com.stackroute.Exceptions.TrackNotFoundException;
import com.stackroute.domain.Music;
import com.stackroute.service.MusicService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MusicControllerTest {


    @Autowired
    private MockMvc mockMvc;

    private Music music;

    @MockBean
    private MusicService musicService;

    @InjectMocks
    private MusicController musicController;

    private List<Music> list=null;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(musicController).build();
        music =new Music();
        music.setTrackName("Let her go!");
        music.setTrackComment("Awesome");
        music.setTrackId(1);
        list=new ArrayList<>();
        list.add(music);
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveMusic() throws TrackAlreadyFoundException,Exception{
        when(musicService.saveMusic(any())).thenReturn(music);
        mockMvc.perform(MockMvcRequestBuilders.post("/music")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllMusic() throws Exception{
        when(musicService.getAllMusic()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/musics")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateMusic() throws Exception {
        when(musicService.updateMusic("value",1)).thenReturn(music);
        when(musicService.saveMusic(any())).thenReturn(music);
        mockMvc.perform(MockMvcRequestBuilders.put("/music/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteMusic() throws TrackNotFoundException,TrackAlreadyFoundException,Exception {
        when(musicService.deleteMusic(1)).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/music/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findById() throws TrackNotFoundException,Exception{
        when(musicService.findById(1)).thenReturn(Optional.of(music));
        mockMvc.perform(MockMvcRequestBuilders.get("/music/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void findByName() throws Exception{
        when(musicService.findByTrackName("s")).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/musics/s")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}