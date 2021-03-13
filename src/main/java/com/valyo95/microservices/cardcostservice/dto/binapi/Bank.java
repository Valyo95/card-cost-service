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
        "name",
        "url",
        "phone",
        "city"
})
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Bank {

    @JsonProperty("name")
    public String name;
    @JsonProperty("url")
    public String url;
    @JsonProperty("phone")
    public String phone;
    @JsonProperty("city")
    public String city;

}
