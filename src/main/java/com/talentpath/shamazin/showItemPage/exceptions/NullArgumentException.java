package com.talentpath.shamazin.showItemPage.exceptions;

public class NullArgumentException extends Exception {
    public NullArgumentException(String message) {
        super(message);
    }

    public NullArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
