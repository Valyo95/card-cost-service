package com.valyo95.microservices.cardcostservice.repository;

import com.valyo95.microservices.cardcostservice.entity.CountryClearingCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryClearingCostRepository extends JpaRepository<CountryClearingCost, String> {
}
