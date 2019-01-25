package com.stackroute.repository;

import com.stackroute.domain.Music;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MusicRepositoryTestIT {

    @Autowired
    private MusicRepository musicRepository;
    private Music music;

    private List<Music> list;

    @Before
    public void setUp() throws Exception {
        music =new Music();
        music.setTrackId(1);
        music.setTrackComment("Awesome");
        music.setTrackName("Let her go!");
        list=new ArrayList<>();
        list.add(music);
    }

    @After
    public void tearDown() throws Exception {
        music=null;

    }
    @Test
    public void testSaveMusic(){
        musicRepository.save(music);
        Music fetchMusic = musicRepository.findById(music.getTrackId()).get();
        Assert.assertEquals(1,fetchMusic.getTrackId());
    }
    @Test
    public void testSaveMusicFailure(){
        Music testMusic = new Music(1,"Let Her Go!","Awesome");
        musicRepository.save(music);
        Music fetchUser = musicRepository.findById(music.getTrackId()).get();
        Assert.assertNotSame(testMusic,music);
    }
    @Test
    public void testGetAllMusic(){
        Music u = new Music(1,"Let her go!","Awesome");
        Music u1 = new Music(2,"Let her go1!","Awesome");

        musicRepository.save(u);
        musicRepository.save(u1);

        List<Music> list = musicRepository.findAll();
        Assert.assertEquals("Let her go1!",list.get(0).getTrackName());
    }
    @Test
    public void testFindById(){
        Music fetchMusic = musicRepository.findById(music.getTrackId()).get();
        Assert.assertEquals(fetchMusic,music);
    }

    @Test
    public void testFindByName(){
        List<Music> fetchMusic = musicRepository.findByTrackName(music.getTrackName());
        Assert.assertEquals(fetchMusic,list);
    }
    @Test
    public void testDelete()
    {
        Music testMusic = new Music(10,"Let Her Go!","Awesome");
        musicRepository.save(testMusic);
        musicRepository.delete(testMusic);
        Assert.assertEquals(Optional.empty() ,musicRepository.findById(10));
    }
    @Test
    public void testUpdate(){
        music.setTrackComment("super");
        musicRepository.save(music);
       Assert.assertEquals("super",musicRepository.findById(1).get().getTrackComment());
    }

    }





