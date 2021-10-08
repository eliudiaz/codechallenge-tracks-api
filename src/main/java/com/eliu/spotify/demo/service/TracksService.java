package com.eliu.spotify.demo.service;

import com.eliu.spotify.demo.data.entity.TrackTbl;
import com.eliu.spotify.demo.data.repository.TracksRepository;
import com.eliu.spotify.demo.endpoint.model.TrackResponseDto;
import com.eliu.spotify.demo.endpoint.model.TrackRequestDto;
import com.eliu.spotify.demo.exception.TrackAlreadyExistsException;
import com.eliu.spotify.demo.exception.TrackNotFoundException;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Track;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class TracksService {
    private final SpotifyApi spotifyApi;
    private final TracksRepository tracksRepository;

    public TrackResponseDto fetchAndCreate(TrackRequestDto request) {
        try {
            var opTrack = tracksRepository.findById(request.getIsrc());
            if (!opTrack.isPresent()) {
                var creds = spotifyApi.clientCredentials().build().execute();
                spotifyApi.setAccessToken(creds.getAccessToken());
                var result = spotifyApi.searchTracks("isrc:" + request.getIsrc()).build().execute();

                if (result.getItems().length > 0) {
                    var track = result.getItems()[0];
                    saveTrack(track, request.getIsrc());
                    return TrackResponseDto.builder()
                            .durationMs(track.getDurationMs())
                            .name(track.getName())
                            .explicit(track.getIsExplicit())
                            .build();
                } else {
                    throw new TrackNotFoundException("Not Found!");
                }
            }
            throw new TrackAlreadyExistsException("Track already exists!");

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException("Error consuming spotify API", e);
        }
    }

    private void saveTrack(Track track, String id) {
        var eTrack = new TrackTbl();
        eTrack.setIsrc(id);
        eTrack.setName(track.getName());
        eTrack.setDurationMs(track.getDurationMs());
        eTrack.setExplicit(track.getIsExplicit());
        tracksRepository.save(eTrack);
    }

    public TrackResponseDto getTrack(String id) {
        var result = tracksRepository.findById(id);
        if (result.isPresent()) {
            var eTrack = result.get();
            return TrackResponseDto.builder().durationMs(eTrack.getDurationMs())
                    .isrc(id)
                    .name(eTrack.getName())
                    .explicit(eTrack.getExplicit())
                    .build();
        } else {
            throw new TrackNotFoundException("Not found!");
        }
    }
}
