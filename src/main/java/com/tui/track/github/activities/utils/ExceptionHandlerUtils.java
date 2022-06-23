package com.tui.track.github.activities.utils;

import com.tui.track.github.activities.openapi.model.JSONAPIErrorDetails;

public class ExceptionHandlerUtils {

    private ExceptionHandlerUtils() {}

    /**
     * build JSONAPIErrorDetails.
     *
     * @param status
     * @param message
     * @return JSONAPIErrorDetails
     */
    public static JSONAPIErrorDetails buildErrorDetails(
            final int status,
            final String message) {
        JSONAPIErrorDetails jsonapiErrorDetails =
                createJsonApiErrorDetails(status, message);
        return jsonapiErrorDetails;
    }

    /**
     * build JSONAPIErrorDetails.
     *
     * @param status
     * @param message
     * @return JSONAPIErrorDetails
     */
    private static JSONAPIErrorDetails createJsonApiErrorDetails(
            final int status,
            final String message) {
        JSONAPIErrorDetails jsonapiErrorDetails = new JSONAPIErrorDetails();
        jsonapiErrorDetails.setStatus(status);
        jsonapiErrorDetails.setMessage(message);
        return jsonapiErrorDetails;
    }

}





