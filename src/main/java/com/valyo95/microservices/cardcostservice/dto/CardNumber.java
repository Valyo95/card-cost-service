package com.valyo95.microservices.cardcostservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.valyo95.microservices.cardcostservice.annotations.Pan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The json representation of the card number
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardNumber {

    @JsonProperty("card_number")
    @Pan
    public String pan;
}
