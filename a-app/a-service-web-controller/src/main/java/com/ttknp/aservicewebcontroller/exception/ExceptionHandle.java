package com.ttknp.aservicewebcontroller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandle {

    /**
        remember it will work after you throw it
        Like. return ... orElseThrow(throw new ContentNotAllowed("There is not exist"))
        Just throw your handler will be enough
    */
    @ExceptionHandler(value = ContentNotAllowed.class)
    private ResponseEntity getNotAllowed(ContentNotAllowed exception){
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .header("Message", exception.getMessage())
                .body(null);
    }

}