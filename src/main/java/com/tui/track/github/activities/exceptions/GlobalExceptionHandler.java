package com.tui.track.github.activities.exceptions;
import com.tui.track.github.activities.openapi.model.JSONAPIErrorDetails;
import com.tui.track.github.activities.utils.ExceptionHandlerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    /**
     * Request header
     */
    public static final String ACCEPT_HEADER = "application/json";

    /**
     * Response content type
     */
    public static final String CONTENT_TYPE = "application/json";

    /** The response content type. */
    public static final String CONTENT_TYPE_STATUS = "application/vnd.com.tui.status.v1+json";


    private JSONAPIErrorDetails createErrorDetails(final int status, final String message) {
        return ExceptionHandlerUtils.buildErrorDetails(status, message);
    }

    @ExceptionHandler(value = { TrackGithubActivitiesException.class})
    public ResponseEntity<JSONAPIErrorDetails> handleTrackGithubServiceException(final TrackGithubActivitiesException exception,
                                                                     final HttpServletRequest request) {
        final JSONAPIErrorDetails errorDetails = createErrorDetails(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return new ResponseEntity<>(errorDetails, getResponseHeaders(request.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { UserNotFoundException.class})
    public ResponseEntity<JSONAPIErrorDetails> handleUserNotFoundException(final UserNotFoundException exception,
                                                                    final HttpServletRequest request) {
        final JSONAPIErrorDetails errorDetails = createErrorDetails(HttpStatus.NOT_FOUND.value(), exception.getReason());

        return new ResponseEntity<>(errorDetails, getResponseHeaders(request.getRequestURI()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { MissingHeaderException.class})
    public ResponseEntity<JSONAPIErrorDetails> handleMissingHeaderException(final MissingHeaderException exception,
                                                                    final HttpServletRequest request) {
        final JSONAPIErrorDetails errorDetails = createErrorDetails(HttpStatus.NOT_ACCEPTABLE.value(), exception.getLocalizedMessage());

        return new ResponseEntity<>(errorDetails, getResponseHeaders(request.getRequestURI()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<JSONAPIErrorDetails> handleWebClientResponseException(final WebClientResponseException exception,
                                                                         final HttpServletRequest request) {
        final JSONAPIErrorDetails errorDetails = createErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails, getResponseHeaders(request.getRequestURI()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static HttpHeaders getResponseHeaders(final String uri) {
        HttpHeaders headers = new HttpHeaders();
        String responseContentType;
        if (uri.endsWith("/status")) {
            responseContentType = CONTENT_TYPE_STATUS;
        } else {
            responseContentType = CONTENT_TYPE;
        }
        headers.add("Content-Type", responseContentType);
        return headers;
    }
}
