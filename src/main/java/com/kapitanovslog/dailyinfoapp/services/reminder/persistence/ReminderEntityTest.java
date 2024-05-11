//package com.kapitanovslog.dailyinfoapp.services.reminder.persistence;
//
//
//import com.kapitanovslog.dailyinfoapp.services.reminder.Reminder;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//
//class ReminderEntityTest implements WithAssertions {
//
//    void domainToEntity() {
//        LocalDate now = LocalDate.now();
//        Reminder reminder = Reminder.builder()
//                .chatId(1L)
//                .reminderAt(now)
//                .reminderText("this is a test")
//                .build();
//        ReminderEntity expected = ReminderEntity.builder()
//                .chatId(1L)
//                .reminderAt(now)
//                .reminderText("this is a test")
//                .createdAt(LocalDateTime.MIN)
//                .build();
//
//        ReminderEntity result = ReminderEntity.toEntity(reminder);
//
//        assertThat(result)
//                .usingRecursiveComparison()
//                .ignoringFields("createdAt")
//                .isEqualTo(expected);
//    }
//
//    void entityToDomain() {
//        LocalDate now = LocalDate.now();
//        ReminderEntity entity = ReminderEntity.builder()
//                .chatId(1L)
//                .reminderAt(now)
//                .reminderText("this is a test")
//                .createdAt(LocalDateTime.MIN)
//                .build();
//        Reminder expected = Reminder.builder()
//                .chatId(1L)
//                .reminderAt(now)
//                .reminderText("this is a test")
//                .build();
//
//        Reminder result = ReminderEntity.toDomain(entity);
//
//        assertThat(result).isEqualTo(expected);
//    }
//
//}