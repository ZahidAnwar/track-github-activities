package com.tui.track.github.activities.wrapper.impl;

import com.tui.track.github.activities.constant.APIMessages;
import com.tui.track.github.activities.exceptions.ServiceUnavailableError;
import com.tui.track.github.activities.exceptions.UserNotFoundException;
import com.tui.track.github.activities.openapi.model.BranchDetails;
import com.tui.track.github.activities.openapi.model.RepositoryDetails;
import com.tui.track.github.activities.wrapper.TrackGithubActivities;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * A wrraper object to handle github queries throw github SDK
 */

@Component
@ConfigurationProperties(prefix="application")
@Slf4j
@ToString
@Getter
@Setter
public class TrackGithubActivitiesImpl implements TrackGithubActivities {


    private String githubAccessToken;
    private String githubOrganization;

    @Override
    public List<RepositoryDetails> getRepositoriesWithUser(String userName) throws IOException {

        if(getGithubAccessToken() == null || getGithubOrganization() == null) {
            throw new ServiceUnavailableError(APIMessages.MISSING_GITHUB_ACCESS_TOKEN_OR_ORGANIZATION_NAME, new IOException());
        }


        List<RepositoryDetails> repoDetails = getRepositoriesWithUser(getGithubAccessToken(), getGithubOrganization(), userName);

        if(repoDetails.size() == 0) {
            throw new UserNotFoundException(APIMessages.USERNAME_NOT_EXISTS_FOR_ORGANIZATION, new RuntimeException());
        }

        return repoDetails;
    }

    @Override
    public List<RepositoryDetails> getRepositoriesWithUser(String accessToken, String org, String userName) throws IOException {

        GitHub gitHub = GitHub.connectUsingOAuth(accessToken);

        GHUser user = gitHub.getUser(userName);
        if(!gitHub.getOrganization(org).hasMember(user)) {
            return new ArrayList<RepositoryDetails>();
        }

        List<RepositoryDetails> repos = new ArrayList<RepositoryDetails>();

        for (GHRepository repo : gitHub.getUser(userName).listRepositories()) {
            RepositoryDetails rd = new RepositoryDetails();
            rd.setRepositoryName(repo.getName());
            rd.setOwnerName(repo.getOwnerName());

            Map<String, GHBranch> branches = repo.getBranches();

            for (Map.Entry<String, GHBranch> set : branches.entrySet()) {
                GHBranch branch = set.getValue();
                BranchDetails bd = new BranchDetails();
                bd.setBranchName(branch.getName());
                bd.setCommitSha(branch.getSHA1());
                rd.addBranchesItem(bd);

            }

            repos.add(rd);
        }

        return repos;
    }

}
