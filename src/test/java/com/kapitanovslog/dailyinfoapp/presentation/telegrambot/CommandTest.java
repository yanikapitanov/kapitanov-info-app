package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class CommandTest {

    @Test
    void nullArgumentThrowsNullPointerException() {
        assertThatNullPointerException()
                .isThrownBy(() -> Command.of(null));
    }

    @Test
    void parseUserInputSuccessfully() {
        Command result = Command.of("/weekly test");
        assertThat(result.getCommand()).isEqualTo("/weekly");
        assertThat(result.getDetails()).isEqualTo("test");
    }

}