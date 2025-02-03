package com.aladdin.demobooksite.exceptions;

import java.text.MessageFormat;

public class ResourceNotFoundException extends GlobalException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public static ResourceNotFoundException of(String message, Object... args) {
        return new ResourceNotFoundException(MessageFormat.format(message, args));
    }


}
