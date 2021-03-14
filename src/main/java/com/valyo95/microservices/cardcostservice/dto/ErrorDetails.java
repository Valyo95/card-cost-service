package com.valyo95.microservices.cardcostservice.dto;

import lombok.Data;
import lombok.Getter;

import java.util.Date;

/**
 * A simple class representing an error
 */
@Data
@Getter
public class ErrorDetails {
    private String error;
    private Date timestamp;
    private String details;

    public ErrorDetails(String error, Date timestamp, String details) {
        this.error = error;
        this.timestamp = timestamp;
        this.details = details;
    }
}