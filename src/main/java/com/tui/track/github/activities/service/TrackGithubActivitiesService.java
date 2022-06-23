package com.tui.track.github.activities.service;

import com.tui.track.github.activities.openapi.model.TrackGithubActivitiesResponse;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface TrackGithubActivitiesService {

    CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> getGithubRepository(String userName, String header);
}
