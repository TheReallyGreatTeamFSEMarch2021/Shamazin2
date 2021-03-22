package com.talentpath.shamazin.showItemPage.exceptions;

public class NoSuchProductPhotoException extends Exception {
    public NoSuchProductPhotoException(String message) {
        super(message);
    }
    public NoSuchProductPhotoException(String message,Throwable innerException) {
        super(message,innerException);
    }
}
