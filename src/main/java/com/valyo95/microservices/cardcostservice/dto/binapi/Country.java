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
        "numeric",
        "alpha2",
        "name",
        "emoji",
        "currency",
        "latitude",
        "longitude"
})
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @JsonProperty("numeric")
    private String numeric;
    @JsonProperty("alpha2")
    private String alpha2;
    @JsonProperty("name")
    private String name;
    @JsonProperty("emoji")
    private String emoji;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("latitude")
    private Integer latitude;
    @JsonProperty("longitude")
    private Integer longitude;

}
