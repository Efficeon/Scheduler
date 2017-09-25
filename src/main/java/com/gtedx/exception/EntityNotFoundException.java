package com.gtedx.exception;

/**
 * Created by lion on 25.09.17.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() { }

    public EntityNotFoundException(String s) {
        super(s);
    }

    public EntityNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EntityNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public EntityNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}