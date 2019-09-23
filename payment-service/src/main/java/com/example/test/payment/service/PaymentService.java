package com.example.test.payment.service;

import com.example.test.payment.DAO.PaymentDAO;
import com.example.test.payment.Domain.PaymentDB;
import com.example.test.payment.Domain.PaymentRequestDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    PaymentDAO paymentDAO;


    public PaymentDB paymentMethod(PaymentRequestDO paymentRequestDO){
        PaymentDB paymentDB=updateDb(paymentRequestDO);
       /* if(paymentRequestDO.getQuantity()==0){
            throw {}
        }*/
        return paymentDAO.save(paymentDB);
    }

    private PaymentDB updateDb(PaymentRequestDO paymentRequestDO){
        PaymentDB pdb= new PaymentDB();
        pdb.setUserId(paymentRequestDO.getUserId());
        pdb.setQuantity(paymentRequestDO.getQuantity());
        pdb.setProductId(paymentRequestDO.getProductId());
        pdb.setPrice(paymentRequestDO.getPrice());
        return pdb;
    }

}
