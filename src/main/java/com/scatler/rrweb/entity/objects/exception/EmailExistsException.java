package com.scatler.rrweb.entity.objects.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmailExistsException extends Exception{
    private String viewname = "/";
    public EmailExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmailExistsException(String message) {
        super(message);
    }
    public EmailExistsException(String message,String viewname) {
        super(message);
        this.viewname = viewname;
    }
}
