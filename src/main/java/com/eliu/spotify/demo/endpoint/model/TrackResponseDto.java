package com.eliu.spotify.demo.endpoint.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TrackResponseDto {
    private String isrc;
    private Integer durationMs;
    private Boolean explicit;
    private String name;
}
