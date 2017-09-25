package com.gtedx.entities;

/**
 * Created by lion on 25.09.17.
 */
public class Request<T> {
    //@Valid
    //@NotNull
    private T body;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
