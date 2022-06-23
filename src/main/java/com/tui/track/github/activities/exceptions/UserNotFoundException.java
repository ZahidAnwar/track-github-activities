package com.tui.track.github.activities.exceptions;

import org.springframework.web.server.ServerWebInputException;

public class UserNotFoundException extends ServerWebInputException {
    public UserNotFoundException(String message, Throwable cause) {
        super(message);
    }
}
