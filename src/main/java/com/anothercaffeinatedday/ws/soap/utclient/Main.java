package com.anothercaffeinatedday.ws.soap.utclient;

import com.anothercaffeinatedday.ws.soap.PaymentProcessor;
import com.anothercaffeinatedday.ws.soap.PaymentProcessorImplService;
import com.anothercaffeinatedday.ws.soap.PaymentProcessorRequest;
import com.anothercaffeinatedday.ws.soap.PaymentProcessorResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The entry point for the payment process SOAP Client.
 */
public class Main {

  public static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
  public static final String WSDL_LOCATION = "http://localhost:8080/javafirstws/services/paymentProcessor?wsdl";

  /**
   * The payment processor SOAP client
   *
   * <p>A simple demonstration of statically defining and calling a service requiring WS-Security options.
   *
   * @param args an array of Strings passed to the runtime
   */
  public static void main(String[] args) {
    try {
      PaymentProcessorImplService service = new PaymentProcessorImplService(new URL(WSDL_LOCATION));
      PaymentProcessor paymentProcessorImplPort = service.getPaymentProcessorImplPort();
      Client client = ClientProxy.getClient(paymentProcessorImplPort);

      Map<String, Object> properties = new HashMap<>();
      properties.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
      properties.put(WSHandlerConstants.USER, "tstone");
      properties.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
      properties.put(WSHandlerConstants.PW_CALLBACK_CLASS, UtPasswordCallback.class.getName());

      WSS4JOutInterceptor outInterceptor = new WSS4JOutInterceptor(properties);
      Endpoint clientEndpoint = client.getEndpoint();
      clientEndpoint.getOutInterceptors().add(outInterceptor);

      PaymentProcessorResponse response = paymentProcessorImplPort.processPayment(new PaymentProcessorRequest());

      LOGGER.debug(Boolean.toString(response.isResult()));
    } catch (MalformedURLException murle) {
      murle.printStackTrace();
    }
  }
}