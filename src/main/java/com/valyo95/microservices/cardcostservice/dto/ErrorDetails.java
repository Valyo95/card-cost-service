package com.valyo95.microservices.cardcostservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * A simple class representing an error
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorDetails implements Serializable {
    @JsonProperty("error")
    private String error;
    @JsonProperty("timestamp")
    private Date timestamp;
    @JsonProperty("details")
    private String details;
}