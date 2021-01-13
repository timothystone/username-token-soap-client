package com.anothercaffeinatedday.ws.soap.utclient;

import com.anothercaffeinatedday.ws.soap.PaymentProcessor;
import com.anothercaffeinatedday.ws.soap.PaymentProcessorImplService;
import com.anothercaffeinatedday.ws.soap.PaymentProcessorRequest;
import com.anothercaffeinatedday.ws.soap.PaymentProcessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    public static final String WSDL_LOCATION = "http://localhost:8080/javafirstws/services/paymentProcessor?wsdl";
    public static void main(String[] args) {
        try {
            PaymentProcessorImplService service = new PaymentProcessorImplService( new URL(WSDL_LOCATION));
            PaymentProcessor paymentProcessorImplPort = service.getPaymentProcessorImplPort();
            PaymentProcessorResponse response = paymentProcessorImplPort.processPayment(new PaymentProcessorRequest());
            LOGGER.debug(Boolean.toString(response.isResult()));
        } catch (MalformedURLException murle) {
            murle.printStackTrace();
        }
    }
}
