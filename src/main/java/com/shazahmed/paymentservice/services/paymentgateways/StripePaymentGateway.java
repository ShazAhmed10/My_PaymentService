package com.shazahmed.paymentservice.services.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway{
    @Override
    public String generateLink() {
        Stripe.apiKey = "sk_test_51OpQN0SHVcsFVOQTcfBwg1ECPaWfwqxUVqfJTzof2hdIwbO42oKJSZoyrH5Vo4UKCvaISvF0O2V4Ciqq0bsGbr900026eT6XRU";
        PaymentLink paymentLink;

        try {
            PriceCreateParams priceParams =
                    PriceCreateParams.builder()
                            .setCurrency("inr")
                            .setUnitAmount(1000L)
                            .setRecurring(
                                    PriceCreateParams.Recurring.builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .build()
                            )
                            .setProductData(
                                    PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                            )
                            .build();

            Price price = Price.create(priceParams);

            PaymentLinkCreateParams paymentParams =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT )
                                    .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                            .setUrl("https://www.google.com")
                                            .build())
                                    .build()
                            )
                            .build();

            paymentLink = PaymentLink.create(paymentParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }


        return paymentLink.getUrl();
    }
}
