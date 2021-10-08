package com.eliu.spotify.demo.exception;

public final class TrackAlreadyExistsException extends RuntimeException {

    public TrackAlreadyExistsException(String message) {
        super(message);
    }

    public TrackAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
