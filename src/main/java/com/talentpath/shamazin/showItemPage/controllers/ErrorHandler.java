package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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

    @ExceptionHandler(value = NullProductPhotoException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason="Product photo toAdd is null")
    public String errorMessageOnNullProductPhotoException(NullArgumentException ex, WebRequest request){
        return request.toString() + ": an error occurred: " + ex.getMessage();
    }

    @ExceptionHandler(value = NullItemException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason="Item is null.")
    public String errorMessageOnNullItemException(NullItemException ex, WebRequest request){
        return request.toString() + ": an error occurred: " + ex.getMessage();
    }

    @ExceptionHandler(value = InvalidIDException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason="ID is not a positive integer.")
    public String errorMessageOnInvalidIDException(InvalidIDException ex, WebRequest request){
        return request.toString() + ": an error occurred: " + ex.getMessage();
    }

    @ExceptionHandler(value = NoSuchProductPhotoException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason="No Product Photo exists with that ID.")
    public String errorMessageOnNoSuchProductPhotoException(NoSuchProductPhotoException ex, WebRequest request){
        return request.toString() + ": an error occurred: " + ex.getMessage();
    }

}
