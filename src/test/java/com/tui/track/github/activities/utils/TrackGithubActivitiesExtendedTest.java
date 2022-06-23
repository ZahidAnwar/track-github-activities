package com.tui.track.github.activities.utils;

import com.tui.track.github.activities.constant.APIMessages;
import com.tui.track.github.activities.exceptions.ServiceUnavailableError;
import com.tui.track.github.activities.wrapper.TrackGithubActivities;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@Tag("unit-test")
public class TrackGithubActivitiesExtendedTest {

    TrackGithubActivities instance = mock(TrackGithubActivities.class);

    /**
     *
     * @throws IOException
     *
     * Assumes application properties file not supplied on the container
     *
     * Unhappy path
     */

    @Test
    @DisplayName("Test github repo details for a user, where no Github access token/organization infos has been supplied")
    @Disabled
    public void testGetGitReposWithMissingGithubAccessToken() throws IOException {

        assertNotNull(instance);
        ServiceUnavailableError exceptionUnderTest = assertThrows(ServiceUnavailableError.class, () -> instance.getRepositoriesWithUser("Test"));
        assertThat(exceptionUnderTest.getMessage(), is(APIMessages.MISSING_GITHUB_ACCESS_TOKEN_OR_ORGANIZATION_NAME));
    }
}
