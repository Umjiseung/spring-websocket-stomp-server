package server.global.security.exception;


import server.global.exception.GlobalException;

public class InvalidTokenException extends GlobalException {
    public InvalidTokenException() {
        super(ErrorCode);
    }
}
