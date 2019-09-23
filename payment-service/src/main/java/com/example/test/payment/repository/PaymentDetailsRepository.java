package com.example.test.payment.repository;

import com.example.test.payment.Domain.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer> {

}
