package com.aladdin.demobooksite.model.dto.response;

public record ErrorResponse(
        int status,
        String message,
        String details,
        String errorTime) {
}
