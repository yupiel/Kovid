package de.yupiel.kovid

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertNotNull

class AppTest {
    @Test
    fun testAppHasAGreeting() {
        val classUnderTest = App()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }
}
