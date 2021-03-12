package com.talentpath.shamazin.showItemPage.exceptions;

public class NoSuchItemException extends Exception {
    public NoSuchItemException(String message) {
        super(message);
    }
    public NoSuchItemException(String message,Throwable innerException) {
        super(message,innerException);
    }
}
