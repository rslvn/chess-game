package com.game.chess.common.exception;

public class InvalidTokenException extends ChessException {
    public InvalidTokenException(Throwable cause, String format, Object... parameters) {
        super(String.format(format, parameters), cause);
    }

    public static InvalidTokenException to(Throwable cause, String format, Object... parameters) {
        return new InvalidTokenException(cause, format, parameters);
    }
}
