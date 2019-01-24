package com.stackroute.config;

import com.stackroute.domain.Music;
import com.stackroute.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


//A Java class decorated with @Component is found during classpath scanning and registered
// in the context as a Spring bean
@Component
//it is used to connect to the property file
@PropertySource("classpath:app2.properties")

public class ApplicationListener implements org.springframework.context.ApplicationListener<ContextRefreshedEvent>{


//    This allows MyListener to be notified when the context has refreshed and
//    one can use that to run arbitrary code when the application context has fully started.
    private MusicService musicService;

    //This will get the constant values which are declared in property
    @Value("${trackId}")
    private int trackId;

    //This will get the constant values which are declared in property
    @Value("${trackName}")
    private String trackName;

    //This will get the constant values which are declared in property
    @Value("${trackComment}")
    private String trackComment;

    //A constructor @Autowired annotation indicates that the constructor should be autowired when creating the bean,
    // even if no <constructor-arg> elements are used while configuring the bean in XML file
    @Autowired
    public ApplicationListener(MusicService musicService) {
        this.musicService = musicService;
    }

    //Application events are available since the very beginning of the Spring framework
// as a mean for loosely coupled components to exchange information.
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Music music = new Music();
        music.setTrackId(trackId);
        music.setTrackName(trackName);
        music.setTrackComment(trackComment);
        try {
            musicService.saveMusic(music);
        }
        catch (Exception e){}
    }


}