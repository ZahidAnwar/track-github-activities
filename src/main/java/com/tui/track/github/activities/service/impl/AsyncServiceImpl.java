package com.tui.track.github.activities.service.impl;

import com.tui.track.github.activities.constant.APIMessages;
import com.tui.track.github.activities.exceptions.MissingHeaderException;
import com.tui.track.github.activities.exceptions.ServiceUnavailableError;
import com.tui.track.github.activities.exceptions.TrackGithubActivitiesException;
import com.tui.track.github.activities.openapi.model.TrackGithubActivitiesResponse;
import com.tui.track.github.activities.service.AsyncService;
import com.tui.track.github.activities.service.TrackGithubActivitiesService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {


    /**
     *
     * The TrackGithubActivitiesService
     *
     */
    private final TrackGithubActivitiesService trackGithubActivitiesService;


    /**
     *
     * Header content document type
     * Accept = application/json
     *
     */
    private static final String CONTENT_TYPE = "application/json";

    public AsyncServiceImpl(TrackGithubActivitiesService trackGithubActivitiesService) {
        this.trackGithubActivitiesService = trackGithubActivitiesService;
    }

    @Override
    public ResponseEntity<TrackGithubActivitiesResponse> getGithubRepository(String userName, String header) {

        if(StringUtils.isEmpty(userName) || StringUtils.isBlank(userName)) {
            throw new TrackGithubActivitiesException(APIMessages.MISSING_PARAM, new RuntimeException());
        }

        if(StringUtils.isEmpty(header) || StringUtils.isBlank(header)
                || !header.equals(CONTENT_TYPE)) {
            throw new MissingHeaderException(APIMessages.MISSING_OR_INVALID_HEADER_VALUE, new RuntimeException());
        }

        ResponseEntity<TrackGithubActivitiesResponse> trackGithubActivitiesResponseJSONAPI = null;


        try {

            CompletableFuture<ResponseEntity<TrackGithubActivitiesResponse>> responseEntityCompletableFuture
                    = trackGithubActivitiesService.getGithubRepository(userName, header);

            trackGithubActivitiesResponseJSONAPI = responseEntityCompletableFuture.get();

        } catch(TrackGithubActivitiesException | InterruptedException | ExecutionException e) {
            new ServiceUnavailableError(APIMessages.INTERNAL_SERVER_ERROR, new RuntimeException());
        }

        return trackGithubActivitiesResponseJSONAPI;
    }


}
