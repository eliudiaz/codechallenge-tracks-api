package com.eliu.spotify.demo.endpoint.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class ErrorResponseDto {
    private String code;
    private String message;
}
