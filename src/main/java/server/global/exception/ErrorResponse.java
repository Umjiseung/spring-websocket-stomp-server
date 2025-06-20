package server.global.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(int status, String message) {}
