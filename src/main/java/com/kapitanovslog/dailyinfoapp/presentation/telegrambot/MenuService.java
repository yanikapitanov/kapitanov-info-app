package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import com.kapitanovslog.dailyinfoapp.model.RequestMessage;
import com.kapitanovslog.dailyinfoapp.services.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
class MenuService {


     private final Map<String, ServiceProvider> serviceProviderRegistry;

    @Autowired
    MenuService(Map<String, ServiceProvider> serviceProviderRegistry) {
        this.serviceProviderRegistry = serviceProviderRegistry;
    }

    String handleRequest(Long chatId, String userInput) {
        Objects.requireNonNull(userInput);
        Objects.requireNonNull(chatId);
        Command command = Command.of(userInput);

        if (command.getDetails().isBlank()) {
            return printHelpMenu();
        }
        log.info("Command: {}", command);
        RequestMessage requestMessage = new RequestMessage(chatId, command.getDetails());

        String result = serviceProviderRegistry.get(command.getCommand()).execute(requestMessage);
        return result.isBlank() ? printHelpMenu() : result;
    }

    public Map<Long, String> findAllRemindersBy(LocalDate reminderAt) {
        return Map.of();
    }

    private static String printHelpMenu() {
        return """
                /pollution - Shows air pollution at a given location using open source data e.g. /pollution Munich
                /weather - Shows current weather for a given location e.g. /weather London
                /transport - Provides disruptions public transport disruptions in Munich for a particular line e.g. /transport s1
                /help - Provides a help menu""";
    }

}
