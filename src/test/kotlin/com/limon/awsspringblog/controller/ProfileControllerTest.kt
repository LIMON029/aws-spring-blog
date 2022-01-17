package com.limon.awsspringblog.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.mock.env.MockEnvironment

internal class ProfileControllerTest {

    @Test
    fun profile() {
        val expectedProfile = "real"
        val env = MockEnvironment()
        env.addActiveProfile(expectedProfile)
        env.addActiveProfile("oauth")
        env.addActiveProfile("real-db")

        val controller = ProfileController(env)

        val profile = controller.profile()

        assertThat(profile).isEqualTo(expectedProfile)
    }
}