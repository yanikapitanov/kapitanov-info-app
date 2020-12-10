package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import com.kapitanovslog.dailyinfoapp.config.TelegramBotConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TelegramCronServiceTest {

    @Mock
    private MenuService menuService;
    @Mock
    private TelegramBotWebClient telegramBotWebClient;
    @InjectMocks
    private TelegramCronService underTest;

    @Test
    void sendReminderSuccessfully() {
        LocalDate today = LocalDate.now();
        Map<Long, String> reminders = Map.of(1L, "Test reminder");
        when(menuService.findAllRemindersBy(today)).thenReturn(reminders);

        underTest.sendReminder();

        verify(telegramBotWebClient).sendMessage(1L, "Test reminder");
    }


}