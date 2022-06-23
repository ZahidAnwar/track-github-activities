package com.tui.track.github.activities.utils;

import com.tui.track.github.activities.constant.APIMessages;
import com.tui.track.github.activities.exceptions.UserNotFoundException;
import com.tui.track.github.activities.wrapper.TrackGithubActivities;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Tag("integration-test")
public class TrackGithubActivitiesTest {


    private final String userName = "ZahidAnwar";


    @Autowired
    TrackGithubActivities instance;



    /**
     *
     * @throws IOException
     *
     * Assumes tui user not configured for this organization, which is set on the
     * application properties file
     *
     * Unhappy path
     */

    @Test
    @DisplayName("Test github repo details for a invalid user")
    public void testGetGitRepos() throws IOException {

        assertNotNull(instance);

        UserNotFoundException exceptionUnderTest = assertThrows(UserNotFoundException.class, () -> instance.getRepositoriesWithUser("tui"));
        assertThat(exceptionUnderTest.getReason(), is(APIMessages.USERNAME_NOT_EXISTS_FOR_ORGANIZATION));
    }

}
