package com.stackroute.config;

import com.stackroute.domain.Music;
import com.stackroute.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:app2.properties")

//When you want to execute some piece of code exactly before the application startup completes
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private MusicService musicService;


    @Autowired
    public CommandLineRunner(MusicService musicService) {
        this.musicService = musicService;
    }

    @Autowired
    Environment environment;


    @Override
    public void run(String... args) throws Exception {
        Music music = new Music();
        music.setTrackId(Integer.parseInt(environment.getProperty(String.valueOf("musicId"))));
        music.setTrackName(environment.getProperty("musicName"));
        music.setTrackComment(environment.getProperty("musicComment"));
        try {
            musicService.saveMusic(music);
        } catch (Exception e) {
        }
    }

}