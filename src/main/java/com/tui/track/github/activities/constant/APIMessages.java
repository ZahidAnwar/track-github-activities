package com.tui.track.github.activities.constant;

public class APIMessages {

    /**
     * Missing param like user name from the url
     */
    public static final String MISSING_PARAM = "Missing user name on the param.";

    /**
     * Missing/invalid header value
     */

    public static final String MISSING_OR_INVALID_HEADER_VALUE = "Missing/invalid  header value.";

    /**
     * Internal server error
     */

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error.";

    /**
     * Service unavailable
     */

    public static final String SERVICE_UNAVAILABLE = "Failed to retrieve users github activities.";

    /**
     * Github access token or organization details not supplied on the application properties or environmental variable for the container
     */

    public static final String MISSING_GITHUB_ACCESS_TOKEN_OR_ORGANIZATION_NAME = "Failed retrieve github access token or organization details from environment variable.";

    /**
     * Supplied user name for the organization not exists on github
     */

    public static final String USERNAME_NOT_EXISTS_FOR_ORGANIZATION = "Supplied user not exists for the organization.";

}
