package com.aladdin.demobooksite.exceptions;

public class GlobalException extends RuntimeException {

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(String message, Throwable throwable) {
    }

}
