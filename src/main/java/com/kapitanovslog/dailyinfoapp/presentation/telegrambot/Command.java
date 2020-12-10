package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        String[] userInputSplit = Objects.requireNonNull(userInput)
                .trim()
                .split("\\s+");
        String command = userInputSplit[0];
        String commandDetails = IntStream.range(1, userInputSplit.length)
                .boxed()
                .map(i -> userInputSplit[i])
                .collect(Collectors.joining(" "));
        return new Command(command, String.join(" ", commandDetails).trim());
    }

}
