package com.tui.track.github.activities.wrapper;

import com.tui.track.github.activities.openapi.model.RepositoryDetails;

import java.io.IOException;
import java.util.List;

public interface TrackGithubActivities {
    public List<RepositoryDetails> getRepositoriesWithUser(String userName) throws IOException;
    public List<RepositoryDetails> getRepositoriesWithUser(String accessToken, String org, String userName) throws IOException;

}
