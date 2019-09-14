package com.scatler.rrweb.entity.objects.exception;

public class NoMoreFreeSeatsException extends RuntimeException {
    public NoMoreFreeSeatsException(String message) {
        super(message);
    }
}
