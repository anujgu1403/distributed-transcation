package com.example.test.payment.DAO;

import com.example.test.payment.Domain.PaymentDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDAO extends JpaRepository<PaymentDB, Long> {

}
