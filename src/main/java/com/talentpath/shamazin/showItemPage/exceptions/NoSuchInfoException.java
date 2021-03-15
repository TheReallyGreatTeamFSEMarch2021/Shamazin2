package com.talentpath.shamazin.showItemPage.exceptions;

public class NoSuchInfoException extends Exception {
    public NoSuchInfoException(String message) {
        super(message);
    }
    public NoSuchInfoException(String message,Throwable innerException) {
        super(message,innerException);
    }
}
