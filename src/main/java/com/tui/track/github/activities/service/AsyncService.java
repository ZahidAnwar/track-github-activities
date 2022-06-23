package com.tui.track.github.activities.service;

import com.tui.track.github.activities.openapi.model.TrackGithubActivitiesResponse;
import org.springframework.http.ResponseEntity;

public interface AsyncService {

    ResponseEntity<TrackGithubActivitiesResponse> getGithubRepository(String userName, String header);
}
