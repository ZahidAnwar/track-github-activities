package com.tui.track.github.activities.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tui.track.github.activities.constant.APIMessages;
import com.tui.track.github.activities.exceptions.MissingHeaderException;
import com.tui.track.github.activities.exceptions.TrackGithubActivitiesException;
import com.tui.track.github.activities.openapi.model.TrackGithubActivitiesResponse;
import com.tui.track.github.activities.service.impl.AsyncServiceImpl;
import com.tui.track.github.activities.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
@Tag("unit-test")
public class AsyncServiceImplTests {

    private AsyncService asyncService;


    @Mock
    private TrackGithubActivitiesService trackGithubActivitiesService;


    @BeforeEach
    void setUp() {
        asyncService = new AsyncServiceImpl(trackGithubActivitiesService);
    }


    @Test
    @DisplayName("Test to get github details")
    void testGetGithubRepository() throws JsonProcessingException, ExecutionException, InterruptedException {

        CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> responseEntityCompletableFuture
                = TestUtils.createTrackGithubActivitiesResponseDto("/data/track-github-activities/track-github-response.json", HttpStatus.OK);


        when(trackGithubActivitiesService.getGithubRepository("ZahidAnwar", "application/json")).thenReturn(responseEntityCompletableFuture);

        ResponseEntity<TrackGithubActivitiesResponse> responseResponseEntity = asyncService.getGithubRepository("ZahidAnwar", "application/json");

        assertNotNull(responseResponseEntity);

        assertEquals(HttpStatus.OK, responseResponseEntity.getStatusCode());
        assertEquals(responseResponseEntity.getBody().getRepositories().get(0).getRepositoryName(), "Android-app-test");

    }

    @Test
    @DisplayName("Test to get github details with invalid/empty param/header")
    void testGetGithubRepositoryForMissingParam() {

        TrackGithubActivitiesException exceptionNoUser = assertThrows(TrackGithubActivitiesException.class, () -> asyncService.getGithubRepository("", "application/json"));
        assertThat(exceptionNoUser.getMessage(), is(APIMessages.MISSING_PARAM));

        exceptionNoUser = assertThrows(TrackGithubActivitiesException.class, () -> asyncService.getGithubRepository(null, "application/json"));
        assertThat(exceptionNoUser.getMessage(), is(APIMessages.MISSING_PARAM));

        MissingHeaderException exceptionNoHeader = assertThrows(MissingHeaderException.class, () -> asyncService.getGithubRepository("Test", ""));
        assertThat(exceptionNoHeader.getMessage(), is(APIMessages.MISSING_OR_INVALID_HEADER_VALUE));

        exceptionNoHeader = assertThrows(MissingHeaderException.class, () -> asyncService.getGithubRepository("Test", null));
        assertThat(exceptionNoHeader.getMessage(), is(APIMessages.MISSING_OR_INVALID_HEADER_VALUE));

        exceptionNoHeader = assertThrows(MissingHeaderException.class, () -> asyncService.getGithubRepository("Test", "test"));
        assertThat(exceptionNoHeader.getMessage(), is(APIMessages.MISSING_OR_INVALID_HEADER_VALUE));

        exceptionNoHeader = assertThrows(MissingHeaderException.class, () -> asyncService.getGithubRepository("Test", "Rest of the world"));
        assertThat(exceptionNoHeader.getMessage(), is(APIMessages.MISSING_OR_INVALID_HEADER_VALUE));

        exceptionNoHeader = assertThrows(MissingHeaderException.class, () -> asyncService.getGithubRepository("Test", "123"));
        assertThat(exceptionNoHeader.getMessage(), is(APIMessages.MISSING_OR_INVALID_HEADER_VALUE));

    }

}
