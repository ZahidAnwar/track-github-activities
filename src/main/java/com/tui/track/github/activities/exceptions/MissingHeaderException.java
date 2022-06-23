package com.tui.track.github.activities.exceptions;

public class MissingHeaderException extends RuntimeException {
    public MissingHeaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
