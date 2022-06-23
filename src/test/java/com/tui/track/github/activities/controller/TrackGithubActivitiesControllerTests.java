package com.tui.track.github.activities.controller;

import com.tui.track.github.activities.openapi.model.TrackGithubActivitiesResponse;
import com.tui.track.github.activities.service.AsyncService;
import com.tui.track.github.activities.utils.TestUtils;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.CompletableFuture;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {TrackGithubActivitiesController.class})
@Import(TrackGithubActivitiesController.class)
@Tag("unit-test")
public class TrackGithubActivitiesControllerTests {

    private static final String TRACK_GITHUB_ACTIVITIES_URI = "/v1/track/user/repositories";

    @MockBean
    private AsyncService asyncService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should response with valid user data")
    void shouldGetGitHubDetailsForValidUser() throws Exception {

        CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> responseEntityCompletableFuture
                = TestUtils.createTrackGithubActivitiesResponseDto("/data/track-github-activities/track-github-response.json", HttpStatus.OK);


        ResponseEntity<TrackGithubActivitiesResponse> responseResponseEntity = responseEntityCompletableFuture.get();

        when(asyncService.getGithubRepository(any(String.class), any(String.class))).thenReturn(responseResponseEntity);

        mockMvc.perform(MockMvcRequestBuilders
                .get(TRACK_GITHUB_ACTIVITIES_URI)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header("Accept", "application/json")
                .param("userName", "ZahidAnwar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.repositories[0].repositoryName", Is.is("Android-app-test")))
                .andExpect(jsonPath("$.repositories[0].ownerName", Is.is("ZahidAnwar")));

    }
}
