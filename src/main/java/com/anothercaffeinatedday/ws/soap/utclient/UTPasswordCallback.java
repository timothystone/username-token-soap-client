package com.anothercaffeinatedday.ws.soap.utclient;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class UTPasswordCallback implements CallbackHandler {
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
