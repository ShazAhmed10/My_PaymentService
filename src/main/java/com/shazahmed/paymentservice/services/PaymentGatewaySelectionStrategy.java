package com.shazahmed.paymentservice.services;

import com.shazahmed.paymentservice.services.paymentgateways.PaymentGateway;
import com.shazahmed.paymentservice.services.paymentgateways.RazorpayPaymentGateway;
import com.shazahmed.paymentservice.services.paymentgateways.StripePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewaySelectionStrategy {
    private RazorpayPaymentGateway razorpayPaymentGateway;
    private StripePaymentGateway stripePaymentGateway;

    @Autowired
    public PaymentGatewaySelectionStrategy(RazorpayPaymentGateway razorpayPaymentGateway,
                                           StripePaymentGateway stripePaymentGateway) {
        this.razorpayPaymentGateway = razorpayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    public PaymentGateway getPaymentGateway(){
        //logic
        //return razorpayPaymentGateway;
        return stripePaymentGateway;
    }
}
