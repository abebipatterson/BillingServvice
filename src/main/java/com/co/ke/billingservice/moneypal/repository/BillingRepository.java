package com.co.ke.billingservice.moneypal.repository;

import com.co.ke.billingservice.moneypal.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends JpaRepository<Billing,Long> {
}
