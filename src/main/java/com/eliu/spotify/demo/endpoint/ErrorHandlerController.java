package com.eliu.spotify.demo.endpoint;

import com.eliu.spotify.demo.endpoint.model.ErrorResponseDto;
import com.eliu.spotify.demo.exception.TrackAlreadyExistsException;
import com.eliu.spotify.demo.exception.TrackNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ErrorHandlerController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TrackNotFoundException.class)
    @ResponseBody
    public ErrorResponseDto handleTrackNotFound(TrackNotFoundException ex) {
        log.error("Captured exception >>", ex);
        return ErrorResponseDto.builder()
                .code("Not_found")
                .message("Track not found!")
                .build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TrackAlreadyExistsException.class)
    @ResponseBody
    public ErrorResponseDto handleTrackAlreadyExists(TrackAlreadyExistsException ex) {
        log.error("Captured exception >>", ex);
        return ErrorResponseDto.builder()
                .code("Already_Exists")
                .message("Track you tried to create already exists!")
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorResponseDto handleTrackAlreadyExists(RuntimeException ex) {
        log.error("Captured exception >>", ex);
        return ErrorResponseDto.builder()
                .code("Unknown")
                .message(ex.getMessage())
                .build();
    }
}
