package com.kapitanovslog.dailyinfoapp.services.reminder;

import com.kapitanovslog.dailyinfoapp.services.reminder.persistence.ReminderPersistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReminderServiceTest {

    @InjectMocks
    private ReminderService underTest;
    @Mock
    private ReminderPersistence reminderPersistence;

    @ParameterizedTest
    @CsvSource({",", "1, ", ", test"})
    void nullArgumentsThrowNPE(Long id, String text) {
        assertThatNullPointerException()
                .isThrownBy(() -> underTest.storeReminder(id, text));

        Mockito.verifyNoInteractions(reminderPersistence);
    }

    @Test
    void emptyTextThrowsIllegalArgException() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> underTest.storeReminder(1L, ""))
                .withMessage("Message cannot be empty or blank");
    }

    @Test
    void storeReminderSuccessfully() {
        Reminder expected = Reminder.builder()
                .chatId(1L)
                .reminderAt(LocalDate.parse("2022-11-11"))
                .reminderText("this is a test")
                .build();
        when(reminderPersistence.save(expected)).thenReturn(expected);

        String result = underTest.storeReminder(1L, "2022-11-11 this is a test");

        assertThat(result).isEqualTo("this is a test saved!");
    }

    @Test
    void findAllRemindersFailsForNullArgument() {
        assertThatNullPointerException().isThrownBy(() -> underTest.findAllRemindersBy(null));
    }

    @Test
    void  findAllRemindersSuccessfully() {
        LocalDate now = LocalDate.now();
        Map<Long, String> expected = Map.of(1L, "Reminders for " + now + "\n\nthis is a test");
        List<Reminder> reminders = List.of(Reminder.builder()
                .chatId(1L)
                .reminderAt(now)
                .reminderText("this is a test")
                .build());
        when(reminderPersistence.findAllRemindersByReminderAt(now)).thenReturn(reminders);

        Map<Long, String> result = underTest.findAllRemindersBy(now);

        assertThat(result).isEqualTo(expected);
    }

}