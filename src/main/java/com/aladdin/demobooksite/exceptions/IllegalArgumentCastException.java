package com.aladdin.demobooksite.exceptions;

import java.text.MessageFormat;

public class IllegalArgumentCastException extends RuntimeException {
    public IllegalArgumentCastException(String message) {
        super(message);
    }

    public static IllegalArgumentCastException of(String message, Object... args) {
        return new IllegalArgumentCastException(MessageFormat.format(message, args));
    }
}
