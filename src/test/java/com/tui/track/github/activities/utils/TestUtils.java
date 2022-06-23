package com.tui.track.github.activities.utils;

import com.tui.track.github.activities.openapi.model.TrackGithubActivitiesResponse;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class TestUtils {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = Jackson2ObjectMapperBuilder.json()
                .build();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }


    public static CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> createTrackGithubActivitiesResponseDto(String filePath, HttpStatus httpStatus)
            throws JsonProcessingException {
        TrackGithubActivitiesResponse trackGithubActivitiesResponse = objectMapper.readValue(
                IOUtils.toString(TrackGithubActivitiesResponse.class.getResourceAsStream(filePath),
                        StandardCharsets.UTF_8), TrackGithubActivitiesResponse.class);
        return CompletableFuture.completedFuture(new ResponseEntity<>(trackGithubActivitiesResponse, httpStatus));
    }


}



