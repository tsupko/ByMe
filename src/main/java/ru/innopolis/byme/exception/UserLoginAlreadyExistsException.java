package ru.innopolis.byme.exception;

public class UserLoginAlreadyExistsException extends RuntimeException{

    public UserLoginAlreadyExistsException(String message) {
        super(message);
    }

    public UserLoginAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
