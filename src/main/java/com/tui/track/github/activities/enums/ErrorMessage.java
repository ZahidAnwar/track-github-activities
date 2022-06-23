package com.tui.track.github.activities.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    USER_NAME_MISSING("Mandatory param user name is missing"),
    DOCUMENT_HEADER_MISSING("Header is missing");


    /**
     * attribute to store the message
     */
    private final String msg;
}
