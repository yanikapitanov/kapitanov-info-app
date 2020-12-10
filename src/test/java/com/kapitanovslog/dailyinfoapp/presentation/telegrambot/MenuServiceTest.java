package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import com.kapitanovslog.dailyinfoapp.services.covid.CovidService;
import com.kapitanovslog.dailyinfoapp.services.pollution.AirPollutionService;
import com.kapitanovslog.dailyinfoapp.services.reminder.ReminderService;
import com.kapitanovslog.dailyinfoapp.services.transport.TelegramBotTransport;
import com.kapitanovslog.dailyinfoapp.services.weather.WeatherBotService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    private WeatherBotService weatherBotService;
    @Mock
    private AirPollutionService airPollutionService;
    @Mock
    private CovidService covidService;
    @Mock
    private TelegramBotTransport telegramBotTransport;
    @Mock
    private ReminderService reminderService;
    @InjectMocks
    private MenuService underTest;


    @ParameterizedTest
    @CsvSource({",", "1, ", ", test"})
    void nullArgumentThrowsNullPointerException(Long id, String text) {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> underTest.handleRequest(id, text));
        verifyNoMoreInteractions(covidService, telegramBotTransport, reminderService, airPollutionService, weatherBotService);
    }

    @Test
    void fetchNoCommandOrDetailsPrintHelpMenu() {
        String expected = "/pollution - Shows air pollution at a given location using open source data e.g. /pollution Munich\n"
                +  "/weekly - Shows weather prediction for the next 7 days for a given location e.g. /weekly London\n"
                +  "/daily - Shows weather prediction in hours for a given location e.g. /daily London\n"
                +  "/covid - Provides Covid19 info per country e.g. /covid Germany\n"
                +  "/transport - Provides disruptions public transport disruptions in Munich for a particular line e.g. /transport s1\n"
                +  "/help - Provides a help menu";
        String result = underTest.handleRequest(1L, "");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void fetchNoDetailsPrintHelpMenu() {
        String expected = "/pollution - Shows air pollution at a given location using open source data e.g. /pollution Munich\n"
              +  "/weekly - Shows weather prediction for the next 7 days for a given location e.g. /weekly London\n"
              +  "/daily - Shows weather prediction in hours for a given location e.g. /daily London\n"
              +  "/covid - Provides Covid19 info per country e.g. /covid Germany\n"
              +  "/transport - Provides disruptions public transport disruptions in Munich for a particular line e.g. /transport s1\n"
              +  "/help - Provides a help menu";
        String result = underTest.handleRequest(1L, "/weekly");

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void fetchWeeklyWeatherSuccessfully() {
        when(weatherBotService.fetchWeeklyWeather("test")).thenReturn("test");
        String result = underTest.handleRequest(1L, "/weekly test");

        assertThat(result).isEqualTo("test");
    }

    @Test
    void fetchDailyWeatherSuccessfully() {
        when(weatherBotService.fetchHourlyWeather("test")).thenReturn("test");
        String result = underTest.handleRequest(1L, "/daily test");

        assertThat(result).isEqualTo("test");
    }

    @Test
    void fetchAirPollutionSuccessfully() {
        when(airPollutionService.findAirPollutionDetails("test")).thenReturn("test");
        String result = underTest.handleRequest(1L, "/pollution test");

        assertThat(result).isEqualTo("test");
    }

    @Test
    void fetchCovidSuccessfully() {
        when(covidService.findCovidInfoByCountry("test")).thenReturn("test");
        String result = underTest.handleRequest(1L, "/covid test");

        assertThat(result).isEqualTo("test");
    }

    @Test
    void fetchTransportSuccessfully() {
        when(telegramBotTransport.provideInterruptions("test")).thenReturn("test");
        String result = underTest.handleRequest(1L, "/transport test");

        assertThat(result).isEqualTo("test");
    }

    @Test
    void storeReminderSuccessfully() {
        when(reminderService.storeReminder(1L,"test")).thenReturn("test");
        String result = underTest.handleRequest(1L, "/reminder test");

        assertThat(result).isEqualTo("test");
    }

    @Test
    void remindersAreSentSuccessfully() {
        LocalDate now = LocalDate.now();
        Map<Long, String> expected = Map.of(1L, "test");
        when(reminderService.findAllRemindersBy(now)).thenReturn(expected);
        Map<Long, String> result = underTest.findAllRemindersBy(now);

        assertThat(result).isEqualTo(expected);
    }



}