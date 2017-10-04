package com.gtedx.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by lion on 25.09.17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private String code;

    private String message;

    private T body;

    private CallbackResponse result;

    public Response(T body) {
        this.body = body;
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public CallbackResponse getResult() {
        return result;
    }

    public void setResult(CallbackResponse result) {
        this.result = result;
    }
}
