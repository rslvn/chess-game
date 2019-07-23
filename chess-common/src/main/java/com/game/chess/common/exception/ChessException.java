package com.game.chess.common.exception;

public class ChessException extends RuntimeException {

    public ChessException(String format, Object... parameters) {
        super(String.format(format, parameters));
    }

    public ChessException(Throwable cause, String format, Object... parameters) {
        super(String.format(format, parameters), cause);
    }

    public static ChessException to(String format, Object... parameters) {
        return new ChessException(format, parameters);
    }

    public static ChessException to(Throwable cause, String format, Object... parameters) {
        return new ChessException(cause, format, parameters);
    }
}
