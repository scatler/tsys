package com.scatler.rrweb.entity.objects.exception;

public class EmailExistsException extends Exception{
    public EmailExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailExistsException(String message) {
        super(message);
    }
}
