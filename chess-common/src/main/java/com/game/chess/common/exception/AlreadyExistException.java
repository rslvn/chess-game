package com.game.chess.common.exception;

public class AlreadyExistException extends ChessException {
    public AlreadyExistException(Throwable cause, String format, Object... parameters) {
        super(String.format(format, parameters), cause);
    }

    public static AlreadyExistException to(Throwable cause, String format, Object... parameters) {
        return new AlreadyExistException(cause, format, parameters);
    }
}
