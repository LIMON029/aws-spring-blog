package com.limon.awsspringblog.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HelloResponseDtoTest {
    @Test
    fun 테스트() {
        val name = "test"
        val amount = 100

        val dto = HelloResponseDto(name, amount)

        assertThat(dto.username).isEqualTo(name)
        assertThat(dto.amount).isEqualTo(amount)
    }
}