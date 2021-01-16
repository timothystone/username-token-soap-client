package com.anothercaffeinatedday.ws.soap.utclient;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.wss4j.common.ext.WSPasswordCallback;

/**
 * The User Token Password Callback for the SOAP Client.
 */
public class UtPasswordCallback implements CallbackHandler {
  @Override
  public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    for (Callback callback : callbacks) {
      WSPasswordCallback passwordCallback = (WSPasswordCallback) callback;
      if (passwordCallback.getIdentifier().equals("tstone")) {
        passwordCallback.setPassword("tstone");
        return;
      }
    }
  }
}
