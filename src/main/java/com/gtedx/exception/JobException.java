package com.gtedx.exception;

public class JobException extends RuntimeException {

    public JobException() {
        super();
    }

    public JobException(String s) {
        super(s);
    }

    public JobException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public JobException(Throwable throwable) {
        super(throwable);
    }

    protected JobException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
