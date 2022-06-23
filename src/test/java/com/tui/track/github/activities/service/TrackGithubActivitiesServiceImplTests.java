package com.tui.track.github.activities.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tui.track.github.activities.openapi.model.TrackGithubActivitiesResponse;
import com.tui.track.github.activities.service.impl.TrackGithubActivitiesServiceImpl;
import com.tui.track.github.activities.utils.TestUtils;
import com.tui.track.github.activities.wrapper.TrackGithubActivities;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit-test")
public class TrackGithubActivitiesServiceImplTests {


    private TrackGithubActivitiesService trackGithubActivitiesService;

    @Mock
    private TrackGithubActivities trackGithubActivities;

    @BeforeEach
    void setUp() { trackGithubActivitiesService = new TrackGithubActivitiesServiceImpl(trackGithubActivities); }

    @Test
    @DisplayName("Test to get github details")
    void testGetGithubRepository() throws JsonProcessingException, ExecutionException, InterruptedException {

        CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> responseEntityCompletableFuture
                = TestUtils.createTrackGithubActivitiesResponseDto("/data/track-github-activities/track-github-response.json", HttpStatus.OK);


        CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> responseResponseEntity
                = trackGithubActivitiesService.getGithubRepository("ZahidAnwar", "application/json");

        assertNotNull(responseResponseEntity);

        assertEquals(HttpStatus.OK, responseResponseEntity.get().getStatusCode());

    }

    @Test
    @DisplayName("Test to get github details for user not exist on the organization")
    @Disabled
    void testGetGithubRepositoryForInvalidUser() throws JsonProcessingException, ExecutionException, InterruptedException {

        CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> responseEntityCompletableFuture
                = TestUtils.createTrackGithubActivitiesResponseDto("/data/track-github-activities/no-data.json", HttpStatus.NOT_FOUND);


        when(trackGithubActivitiesService.getGithubRepository("123", "application/json")).thenReturn(responseEntityCompletableFuture);

        CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> responseResponseEntity
                = trackGithubActivitiesService.getGithubRepository("123", "application/json");

        assertNotNull(responseResponseEntity);

        assertEquals(HttpStatus.NOT_FOUND, responseResponseEntity.get().getStatusCode());

    }
}
