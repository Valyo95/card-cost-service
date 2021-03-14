package com.valyo95.microservices.cardcostservice.dto.binapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "length",
        "luhn"
})
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Number {

    @JsonProperty("length")
    private Integer length;
    @JsonProperty("luhn")
    private Boolean luhn;

}
