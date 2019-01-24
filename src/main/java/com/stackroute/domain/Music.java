package com.stackroute.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data

public class Music {

    @Id
    private int trackId; //id for Music class
    private String trackName;//name for Music class
    private String trackComment;//comment for Music class

}
