package com.eliu.spotify.demo.endpoint;

import com.eliu.spotify.demo.endpoint.model.TrackResponseDto;
import com.eliu.spotify.demo.endpoint.model.TrackRequestDto;
import com.eliu.spotify.demo.service.TracksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@Api(value="Tracks API")
@RestController
@RequestMapping("/api/v1/codechallenge")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TracksController {

    private final TracksService service;


    @ApiOperation(value = "Create a new track by sending the ISRC")
    @PostMapping("/tracks")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Valid TrackRequestDto request) {
        service.fetchAndCreate(request);
    }


    @ApiOperation(value = "Fetch tracks by ISRC")
    @GetMapping("/tracks/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TrackResponseDto findById(@PathVariable(name = "id") String id) {
        return service.getTrack(id);
    }


}
