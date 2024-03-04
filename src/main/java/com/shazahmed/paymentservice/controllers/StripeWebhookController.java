package com.shazahmed.paymentservice.controllers;

import com.stripe.model.Event;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebhook")
public class StripeWebhookController {

    @PostMapping
    public void forAllEvents(Event event){
        System.out.println("Receiving webhook for event: " + event.toString());
    }
}
