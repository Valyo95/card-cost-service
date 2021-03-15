package com.valyo95.microservices.cardcostservice.entity;

import com.valyo95.microservices.cardcostservice.annotations.Country;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Table(name = "country_clearing_cost")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CountryClearingCost {
    @Id
    @Column(name = "country_code", length = 2, updatable = false)
    @Country
    private String countryCode;

    @DecimalMin(value = "0.0")
    @Column(name = "cost")
    private BigDecimal cost;

}



