package com.tijo.streaming.impl.collectors;

import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.Message;

public class PolarisFutureCallback implements  FutureCallback {
    private String name;
    PolarisFutureCallback(String name ){
        this.name =name;
    }

    @Override
    public void completed(Object o) {

    }

    @Override
    public void failed(final Exception ex) {
        System.out.println( ex);
    }

    @Override
    public void cancelled() {
        System.out.println( " cancelled");
    }
}
