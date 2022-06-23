package com.tui.track.github.activities.controller;
import com.tui.track.github.activities.openapi.api.V1Api;
import com.tui.track.github.activities.openapi.model.TrackGithubActivitiesResponse;
import com.tui.track.github.activities.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class TrackGithubActivitiesController implements V1Api {


    /**
     * The AsyncService
     */
    private final AsyncService asyncService;

    public TrackGithubActivitiesController(final AsyncService asyncService) { this.asyncService = asyncService; }

    @Override
    public ResponseEntity<TrackGithubActivitiesResponse> getGithubRepository(String userName, String header) {

        return asyncService.getGithubRepository(userName, header);
    }
}

