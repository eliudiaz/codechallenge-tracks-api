package com.eliu.spotify.demo.endpoint.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TrackRequestDto {
    @NotEmpty
    private String isrc;
}
