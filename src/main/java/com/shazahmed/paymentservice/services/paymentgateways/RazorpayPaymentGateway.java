package com.shazahmed.paymentservice.services.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class RazorpayPaymentGateway implements PaymentGateway{
    @Override
    public String generateLink() {
        PaymentLink payment;

        try {
            RazorpayClient razorpay = new RazorpayClient
                    ("rzp_test_Cm34Et9AkyTOjw",
                            "g9Bea3vymga9LZjC4Og7u8YN");

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",1000);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",false);
            paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("expire_by",1709337600);
            paymentLinkRequest.put("reference_id","TS1990");
            paymentLinkRequest.put("description","Payment for policy no #23456");

            JSONObject customer = new JSONObject();
            customer.put("name","+919538455288");
            customer.put("contact","Shaz Ahmed");
            customer.put("email","5191sak@gmail.com");

            paymentLinkRequest.put("customer",customer);

            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);

            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("reminder_enable",false);

            paymentLinkRequest.put("callback_url","https://google.com/");
            paymentLinkRequest.put("callback_method","get");

            payment = razorpay.paymentLink.create(paymentLinkRequest);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

        return payment.toString();
    }
}
