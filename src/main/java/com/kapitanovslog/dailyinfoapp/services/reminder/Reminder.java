package com.kapitanovslog.dailyinfoapp.services.reminder;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class Reminder {

    private final Long chatId;
    private final LocalDate reminderAt;
    private final String reminderText;

}
