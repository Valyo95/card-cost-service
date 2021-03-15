package com.valyo95.microservices.cardcostservice.dto;

import com.valyo95.microservices.cardcostservice.annotations.Country;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ToString
public class CountryClearingCostDTO {
    @Country
    private String countryCode;

    @DecimalMin(value = "0.0")
    private BigDecimal cost;

}



