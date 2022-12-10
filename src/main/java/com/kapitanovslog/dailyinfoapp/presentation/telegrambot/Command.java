package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Getter
@ToString
@EqualsAndHashCode
class Command {
    private final String command;
    private final String details;

    private Command(String command, String details) {
       this.command = command;
       this.details = details;
    }

    static Command of(String userInput) {
        System.out.println(userInput);
        Objects.requireNonNull(userInput);
        String command = parseUserInput(userInput, s -> s.startsWith("/"), "");
        String commandDetails = parseUserInput(userInput, s -> !s.startsWith("/"), " ");
        return new Command(command, commandDetails);
    }

    private static String parseUserInput(String userInputSplit, Predicate<String> filterString, String delimiter) {
        return Arrays.stream(userInputSplit.trim().split("\\s+"))
                .filter(filterString)
                .map(String::trim)
                .collect(Collectors.joining(delimiter));
    }

}
