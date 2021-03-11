package com.talentpath.shamazin.showItemPage.controllers;

import com.talentpath.shamazin.showItemPage.exceptions.NoSuchItemFamilyException;
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

}
