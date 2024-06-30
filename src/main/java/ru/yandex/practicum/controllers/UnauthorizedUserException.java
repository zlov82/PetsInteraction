package ru.yandex.practicum.controllers;

public class UnauthorizedUserException extends RuntimeException{
    private final String user;
    private final String owner;

    public UnauthorizedUserException(String user, String owner) {
        super();
        this.user = user;
        this.owner = owner;
    }

    public String getUser() {
        return user;
    }

    public String getOwner() {
        return owner;
    }
}
