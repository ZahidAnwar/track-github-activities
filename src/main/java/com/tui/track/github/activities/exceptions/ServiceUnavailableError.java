package com.tui.track.github.activities.exceptions;

public class ServiceUnavailableError extends RuntimeException {

    public ServiceUnavailableError(String message, Throwable cause) {
        super(message, cause);
    }
}
