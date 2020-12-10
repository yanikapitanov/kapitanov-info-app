package com.kapitanovslog.dailyinfoapp.services.reminder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table("reminder")
class ReminderEntity {

    @Id
    private final Long id;
    private final Long chatId;
    private final LocalDate reminderAt;
    private final String reminderText;
    private final LocalDateTime createdAt;


    static ReminderEntity toEntity(Reminder reminder) {
        return ReminderEntity.builder()
                .reminderAt(reminder.getReminderAt())
                .reminderText(reminder.getReminderText())
                .chatId(reminder.getChatId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    static Reminder toDomain(ReminderEntity entity) {
        return Reminder.builder()
                .chatId(entity.getChatId())
                .reminderAt(entity.getReminderAt())
                .reminderText(entity.getReminderText())
                .build();
    }
}
