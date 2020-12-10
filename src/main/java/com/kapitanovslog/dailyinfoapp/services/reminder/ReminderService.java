package com.kapitanovslog.dailyinfoapp.services.reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;

@Service
public class ReminderService {

    private final ReminderPersistence reminderPersistence;

    @Autowired
    ReminderService(ReminderPersistence reminderPersistence) {
        this.reminderPersistence = reminderPersistence;
    }

    public String storeReminder(Long chatId, String message) {
        Objects.requireNonNull(chatId);
        Objects.requireNonNull(message);
        if (!StringUtils.hasText(message)) {
            throw new IllegalArgumentException("Message cannot be empty or blank");
        }

        String[] splitMessage = message.split("\\s+");
        LocalDate reminderAt = LocalDate.parse(splitMessage[0]);
        String reminderText = IntStream.range(1, splitMessage.length)
                .boxed()
                .map(i -> splitMessage[i])
                .collect(Collectors.joining(" "));
        Reminder reminder = Reminder.builder()
                .reminderAt(reminderAt)
                .reminderText(reminderText)
                .chatId(chatId)
                .build();
        return reminderPersistence.save(reminder).getReminderText() + " saved!";
    }

    public Map<Long, String> findAllRemindersBy(LocalDate reminderAt) {
        Objects.requireNonNull(reminderAt);
        return reminderPersistence.findAllRemindersByReminderAt(reminderAt).stream()
                .collect(Collectors.groupingBy(
                        Reminder::getChatId,
                        mapping(Reminder::getReminderText, joining("\n",
                                "Reminders for " + reminderAt + "\n\n",
                                ""))));
    }

}
