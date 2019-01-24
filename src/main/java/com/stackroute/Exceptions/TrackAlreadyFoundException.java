package com.stackroute.Exceptions;

public class TrackAlreadyFoundException extends Exception {

    private String message;

    public TrackAlreadyFoundException() {
    }


    public TrackAlreadyFoundException(String message) {
        super(message);
        this.message = message;
    }
}
