package com.event.management.application.exceptions;

import com.event.management.infrastructure.models.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<ResponseError> bussinesExceptionHandler(BussinesException ex){
        ResponseError error = new ResponseError(ex.getMessage(),ex.getStatus().value());
        return new ResponseEntity<>(error,ex.getStatus());
    }
}
