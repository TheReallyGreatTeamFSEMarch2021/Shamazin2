package com.talentpath.shamazin.showItemPage.exceptions;

public class NullAnswerException extends Exception{

    public NullAnswerException(String message){
        super(message);
    }

    public NullAnswerException(String message, Throwable innerException){
        super(message, innerException);
    }
}
