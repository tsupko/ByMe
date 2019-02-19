package ru.innopolis.byme.dao;

public class UserLoginAlreadyExistsExeption extends Exception {

    public UserLoginAlreadyExistsExeption(String message) {
        super(message);
    }
}
