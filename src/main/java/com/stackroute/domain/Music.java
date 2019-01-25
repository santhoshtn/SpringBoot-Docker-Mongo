package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Music {

    @Id
    private int trackId; //id for Music class
    private String trackName;//name for Music class
    private String trackComment;//comment for Music class

}
