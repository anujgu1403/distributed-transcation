package com.example.test.payment.Controller;

import com.example.test.payment.Domain.PaymentDB;
import com.example.test.payment.Domain.PaymentRequestDO;
import com.example.test.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/payment")
    public ResponseEntity getPaymentDetails(@RequestBody PaymentRequestDO paymentRequestDO){
        System.out.println("coming here");
        PaymentDB paymentDB=paymentService.paymentMethod(paymentRequestDO);
        return (ResponseEntity) ResponseEntity.ok();
    }
    @RequestMapping(value = "/test")
    public String testHello(){
        return "hello";
    }
}
