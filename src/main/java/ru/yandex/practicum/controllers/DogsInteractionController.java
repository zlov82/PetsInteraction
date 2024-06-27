package ru.yandex.practicum.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dogs")
public class DogsInteractionController {
    private int happiness = 0;

    @GetMapping("/converse")
    public Map<String, String> converse() {
        // проверка happiness
        happiness += 2;
        return Map.of("talk", "Гав!");
    }

    @GetMapping("/happiness")
    public Map<String, Integer> happiness() {
        return Map.of("happiness", happiness);
    }

    @GetMapping("/pet")
    public Map<String, String> pet(@RequestParam(required = false) final Integer count) {
        if (count == null) {
            throw new IncorrectCountException("Параметр count равен null.");
        }
        if (count <= 0) {
            throw new IncorrectCountException("Параметр count имеет отрицательное значение.");
        }

        // проверка happiness
        if (happiness + count > 10) {
            throw new HappinessOverflowException("Осторожно, вы так избалуете пёсика!", happiness);
        }

        happiness += count;
        return Map.of("action", "Вильнул хвостом. ".repeat(count));
    }

    @ExceptionHandler
    public Map<String, String> handleIncorrectCount(final IncorrectCountException e) {
        return Map.of(
                "error", "Ошибка с параметром count.",
                "errorMessage", e.getMessage()
        );
    }

    // метод handleHappinessOverflow
    @ExceptionHandler
    public Map<String,String> handleHappinessOverflow(final HappinessOverflowException e) {
        return Map.of(
                "happinessLevel", e.getHappinessLevel().toString(),
                "error", "Осторожно, вы так избалуете пёсика!"
        );
    }
}
