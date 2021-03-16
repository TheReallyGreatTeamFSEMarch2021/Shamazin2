package com.talentpath.shamazin.showItemPage.exceptions;

public class NullQuestionException extends Exception{

    public NullQuestionException(String message){
        super(message);
    }

    public NullQuestionException(String message, Throwable innerException){
        super(message, innerException);
    }
}
