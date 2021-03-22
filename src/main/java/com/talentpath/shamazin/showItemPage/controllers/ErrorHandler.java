package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemException;
import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemFamilyException;
import com.talentpath.shamazin.showItemPage.exceptions.NullArgumentException;
import com.talentpath.shamazin.showItemPage.exceptions.NullReviewException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = NoSuchItemFamilyException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String errorMessageOnNoSuchItemFamilyException(NoSuchItemFamilyException ex, WebRequest request) {
        return request.toString() + ": an error occurred: " + ex.getMessage();
    }

    @ExceptionHandler(value = NoSuchItemException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String errorMessageOnNoSuchItemException(NoSuchItemException ex, WebRequest request) {
        return request.toString() + ": an error occurred: " + ex.getMessage();
    }

    @ExceptionHandler(value = NullReviewException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String errorMessageOnNullReviewException(NullReviewException ex, WebRequest request){
        return request.toString() + ": an error occurred: " + ex.getMessage();
    }

    @ExceptionHandler(value = NullArgumentException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String errorMessageOnNullArgumentException(NullArgumentException ex, WebRequest request){
        return request.toString() + ": an error occurred: " + ex.getMessage();
    }

    @ExceptionHandler(value= NoSuchElementException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String errorMessageOnNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        return request.toString() + ": an error occured: " + ex.getMessage();
    }


}
