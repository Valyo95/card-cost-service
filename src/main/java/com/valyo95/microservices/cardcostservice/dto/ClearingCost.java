package com.valyo95.microservices.cardcostservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClearingCost {
    @JsonProperty("cost")
    @PositiveOrZero
    private BigDecimal cost;
}
