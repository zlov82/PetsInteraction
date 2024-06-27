package ru.yandex.practicum.controllers;

import lombok.Getter;

@Getter
public class HappinessOverflowException extends RuntimeException{
    private Integer happinessLevel;

    public HappinessOverflowException(String message, Integer happinessLevel) {
        super(message);
        this.happinessLevel = happinessLevel;
    }
}
