package com.shazahmed.paymentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy;

    @Autowired
    public PaymentService(PaymentGatewaySelectionStrategy paymentGatewaySelectionStrategy) {
        this.paymentGatewaySelectionStrategy = paymentGatewaySelectionStrategy;
    }

    public String initiatePayment(){
        return paymentGatewaySelectionStrategy
                .getPaymentGateway()
                .generateLink();
    }
}
