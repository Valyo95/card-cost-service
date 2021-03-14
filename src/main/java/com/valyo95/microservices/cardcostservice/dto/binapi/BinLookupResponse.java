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
        "number",
        "scheme",
        "type",
        "brand",
        "prepaid",
        "country",
        "bank"
})
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BinLookupResponse {

    @JsonProperty("number")
    private Number number;
    @JsonProperty("scheme")
    private String scheme;
    @JsonProperty("type")
    private String type;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("prepaid")
    private Boolean prepaid;
    @JsonProperty("country")
    private Country country;
    @JsonProperty("bank")
    private Bank bank;

}
