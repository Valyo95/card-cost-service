package com.valyo95.microservices.cardcostservice.repository;

import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryClearingCostRepository extends JpaRepository<CountryClearingCost, String> {
    Optional<CountryClearingCost> findByCountryCodeIgnoreCase(String s);
}
