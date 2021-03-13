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
    public Number number;
    @JsonProperty("scheme")
    public String scheme;
    @JsonProperty("type")
    public String type;
    @JsonProperty("brand")
    public String brand;
    @JsonProperty("prepaid")
    public Boolean prepaid;
    @JsonProperty("country")
    public Country country;
    @JsonProperty("bank")
    public Bank bank;

}
