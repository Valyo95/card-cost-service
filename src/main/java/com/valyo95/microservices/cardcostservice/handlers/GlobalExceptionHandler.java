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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> resourceNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " " + error.getDefaultMessage());
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

//    @ExceptionHandler(WebExchangeBindException::class)
//    fun handleWebExchangeBindException(e: WebExchangeBindException): HttpStatus {
//        throw object : WebExchangeBindException(e.methodParameter!!, e.bindingResult) {
//            override val message = "${fieldError?.field} has invalid value '${fieldError?.rejectedValue}'"
//        }
//    }
}