package org.web.excepciones;

public class BussinessNotFoundException extends RuntimeException {

    public BussinessNotFoundException(String message) {
        super(message);
    }

    public BussinessNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}