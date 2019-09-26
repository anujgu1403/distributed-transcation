package com.example.test.payment.Controller;

import com.example.test.payment.Domain.PaymentRequest;
import com.example.test.payment.Domain.Response;
import com.example.test.payment.service.PaymentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    PaymentServiceImpl paymentServiceImpl;

    @PostMapping(value = "/updatePayment")
    public ResponseEntity<Response> getPaymentDetails(@RequestBody PaymentRequest paymentRequest){
    	ResponseEntity<Response> responseEntity=null;
    	logger.info("PaymentRequest: "+paymentRequest);
        Response resp=paymentServiceImpl.updatePaymentDetails(paymentRequest);
        logger.info("Response: " + resp);
		if (resp.getCode().equals("201")) {
			responseEntity = new ResponseEntity<Response>(resp, HttpStatus.CREATED);
		}
		if (resp.getCode().equals("400")) {
			responseEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		if (resp.getCode().equals("500")) {
			responseEntity = new ResponseEntity<Response>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
    }
    
    @PostMapping(value = "/revertPayment")
    public ResponseEntity<Response> revertPaymentTransaction(@RequestBody PaymentRequest paymentRequest){
    	ResponseEntity<Response> responseEntity=null;
    	logger.info("PaymentRequest: "+paymentRequest);
        Response resp=paymentServiceImpl.revertPaymentTransaction(paymentRequest);
        logger.info("Response: " + resp);
		if (resp.getCode().equals("201")) {
			responseEntity = new ResponseEntity<Response>(resp, HttpStatus.CREATED);
		}
		if (resp.getCode().equals("400")) {
			responseEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		if (resp.getCode().equals("500")) {
			responseEntity = new ResponseEntity<Response>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
    }
}
