package com.kapitanovslog.dailyinfoapp.services.reminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
class ReminderPersistence {

    private final ReminderRepository reminderRepository;

    @Autowired
    ReminderPersistence(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    List<Reminder> findAllRemindersByReminderAt(LocalDate reminderAt) {
        return reminderRepository.findByReminderAt(reminderAt).stream()
                .map(ReminderEntity::toDomain)
                .collect(Collectors.toList());
    }

    Reminder save(Reminder reminder) {
        ReminderEntity toBeSaved = ReminderEntity.toEntity(reminder);
        ReminderEntity saved = reminderRepository.save(toBeSaved);
        return ReminderEntity.toDomain(saved);
    }

}
