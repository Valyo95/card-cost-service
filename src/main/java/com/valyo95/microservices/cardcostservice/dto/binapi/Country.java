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
    public String numeric;
    @JsonProperty("alpha2")
    public String alpha2;
    @JsonProperty("name")
    public String name;
    @JsonProperty("emoji")
    public String emoji;
    @JsonProperty("currency")
    public String currency;
    @JsonProperty("latitude")
    public Integer latitude;
    @JsonProperty("longitude")
    public Integer longitude;

}
