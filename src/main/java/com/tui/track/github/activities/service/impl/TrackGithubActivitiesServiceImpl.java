package com.tui.track.github.activities.service.impl;

import com.tui.track.github.activities.constant.APIMessages;
import com.tui.track.github.activities.exceptions.ServiceUnavailableError;
import com.tui.track.github.activities.openapi.model.RepositoryDetails;
import com.tui.track.github.activities.openapi.model.TrackGithubActivitiesResponse;
import com.tui.track.github.activities.service.TrackGithubActivitiesService;
import com.tui.track.github.activities.wrapper.TrackGithubActivities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class TrackGithubActivitiesServiceImpl implements TrackGithubActivitiesService {



    /**
     *
     *  TrackGithubActivitiesImpl.
     */

    private final TrackGithubActivities trackGithubActivities;



    public TrackGithubActivitiesServiceImpl(final TrackGithubActivities trackGithubActivities) {
        this.trackGithubActivities = trackGithubActivities;
    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> getGithubRepository(String userName, String header) {


        log.info("User name provided on the request {}", userName);
        List<RepositoryDetails> repositoryDetails = new ArrayList<RepositoryDetails>();

        try {
           repositoryDetails = trackGithubActivities.getRepositoriesWithUser(userName);
        }catch(IOException e) {
            throw new ServiceUnavailableError(APIMessages.SERVICE_UNAVAILABLE, e);
        }

        TrackGithubActivitiesResponse trackGithubActivitiesResponse = new TrackGithubActivitiesResponse();
        trackGithubActivitiesResponse.setRepositories(repositoryDetails);

        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.OK).body(trackGithubActivitiesResponse));
    }


}
