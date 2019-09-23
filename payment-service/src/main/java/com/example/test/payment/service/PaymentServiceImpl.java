package com.example.test.payment.service;

import com.example.test.payment.Domain.PaymentDetails;
import com.example.test.payment.Domain.PaymentRequest;
import com.example.test.payment.Domain.Response;
import com.example.test.payment.repository.PaymentDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl {
	private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	PaymentDetailsRepository paymentDetailsRepository;

	public Response updatePaymentDetails(PaymentRequest paymentRequest) {
		Response response = new Response();
		try {
			if (!paymentRequest.getOrderId().isEmpty()) {
				PaymentDetails paymentDetails = new PaymentDetails();
				paymentDetails.setOrderId(paymentRequest.getOrderId());
				paymentDetails.setAmount(paymentRequest.getAmount());
				paymentDetails = paymentDetailsRepository.save(paymentDetails);
				if (paymentDetails.getId() > 0) {
					response.setCode("201");
					response.setMessage("Successfully updated payment details.");
				}
			} else {
				response.setCode("400");
				response.setMessage("Bad request.");
			}
		} catch (Exception e) {
			logger.error("Error while updating payment details: ", e.getCause());
			response.setCode("500");
			response.setMessage("Error while updating payment details.");
		}
		logger.info("Response: " + response);
		return response;
	}
}
