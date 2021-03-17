package com.talentpath.shamazin.showItemPage.exceptions;

import javax.validation.constraints.Null;

public class NullProductPhotoException extends Exception{
    public NullProductPhotoException(String message){super(message);}
    public NullProductPhotoException(String message, Throwable innerException){super(message, innerException);}
}
