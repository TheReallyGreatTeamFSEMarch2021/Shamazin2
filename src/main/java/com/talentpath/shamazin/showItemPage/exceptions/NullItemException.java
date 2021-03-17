package com.talentpath.shamazin.showItemPage.exceptions;


public class NullItemException extends Exception {
    public NullItemException(String message){super(message);}
    public NullItemException(String message, Throwable innerException){super(message, innerException);}
}
