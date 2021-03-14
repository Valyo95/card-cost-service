package com.valyo95.microservices.cardcostservice.handlers;

import com.valyo95.microservices.cardcostservice.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler used specifically for validation errors
     *
     * @param ex      the exception
     * @param request the request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationErrorExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + "='" + error.getRejectedValue() + "' " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + " " + error.getDefaultMessage());
        }
        String error = errors
                .stream()
                .collect(Collectors.joining(", "));

        ErrorDetails errorDetails = new ErrorDetails(error, new Date(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    /**
     * General exception handler for all kind of exceptions
     *
     * @param ex      the exception
     * @param request the request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> binApiClientExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}