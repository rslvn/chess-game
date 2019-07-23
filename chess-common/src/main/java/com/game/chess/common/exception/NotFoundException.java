package com.game.chess.common.exception;

public class NotFoundException extends ChessException {
    public NotFoundException(Throwable cause, String format, Object... parameters) {
        super(String.format(format, parameters), cause);
    }

    public static NotFoundException to(Throwable cause, String format, Object... parameters) {
        return new NotFoundException(cause, format, parameters);
    }
}
