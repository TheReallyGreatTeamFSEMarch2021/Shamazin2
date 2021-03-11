package com.talentpath.shamazin.showItemPage.exceptions;

public class NullReviewException extends Exception{

    public NullReviewException(String message){ super(message); }

    public NullReviewException(String message, Throwable innerException){ super(message, innerException); }
}
