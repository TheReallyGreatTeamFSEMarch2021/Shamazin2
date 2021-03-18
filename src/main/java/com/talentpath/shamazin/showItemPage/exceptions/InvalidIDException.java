package com.talentpath.shamazin.showItemPage.exceptions;

import net.bytebuddy.implementation.bytecode.Throw;

public class InvalidIDException extends Exception{
    public InvalidIDException(String message){super(message);}
    public InvalidIDException(String message, Throwable innerException){super(message, innerException);}
}
