package com.talentpath.shamazin.showItemPage.exceptions;

public class NoSuchItemFamilyException extends Exception {
    public NoSuchItemFamilyException(String message) {
        super(message);
    }
    public NoSuchItemFamilyException(String message,Throwable innerException) {
        super(message,innerException);
    }
}
