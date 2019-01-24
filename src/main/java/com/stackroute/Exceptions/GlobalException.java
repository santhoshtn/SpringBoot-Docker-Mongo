package com.stackroute.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


//this annotation is used to specify global exception handling
@ControllerAdvice
public class GlobalException {

    //this annotation is used to map each
        @ExceptionHandler(TrackNotFoundException.class)
        public ResponseEntity handleTrackNotFoundException(final Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
        }

        @ExceptionHandler(TrackAlreadyFoundException.class)
        public ResponseEntity TrackAlreadyFoundException(final Exception ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
    }



