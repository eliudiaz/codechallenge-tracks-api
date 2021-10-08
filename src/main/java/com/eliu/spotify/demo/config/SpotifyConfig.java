package com.eliu.spotify.demo.config;

import com.wrapper.spotify.SpotifyApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpotifyConfig {

    @Bean
    public SpotifyApi buildSpotifyApi(@Value("${spotify.client-id}") String clientId,
                                      @Value("${spotify.client-secret}") String clientSecret) {
        return new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    }
}
