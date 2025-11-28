package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*

class ToggleLiteTest {

    @BeforeEach
    fun setup() {
        ToggleLite.clear()
    }

    @Test
    fun `set and isEnabled work correctly`() {
        ToggleLite.set("feature_a", true)
        assertTrue(ToggleLite.isEnabled("feature_a"))

        ToggleLite.set("feature_b", false)
        assertFalse(ToggleLite.isEnabled("feature_b"))
    }

    @Test
    fun `isEnabled returns default when flag not set`() {
        assertFalse(ToggleLite.isEnabled("unknown"))
        assertTrue(ToggleLite.isEnabled("unknown", default = true))
    }

    @Test
    fun `toggle inverts flag value`() {
        ToggleLite.set("feature", false)
        ToggleLite.toggle("feature")
        assertTrue(ToggleLite.isEnabled("feature"))

        ToggleLite.toggle("feature")
        assertFalse(ToggleLite.isEnabled("feature"))
    }

    @Test
    fun `observe notifies on change`() {
        var notified = false
        var receivedValue = false

        ToggleLite.observe("feature") { enabled ->
            notified = true
            receivedValue = enabled
        }

        ToggleLite.set("feature", true)

        assertTrue(notified)
        assertTrue(receivedValue)
    }

    @Test
    fun `observe returns unsubscribe function`() {
        var callCount = 0
        val unsubscribe = ToggleLite.observe("feature") { callCount++ }

        ToggleLite.set("feature", true)
        assertEquals(1, callCount)

        unsubscribe()
        ToggleLite.set("feature", false)
        assertEquals(1, callCount)
    }

    @Test
    fun `setAll configures multiple flags`() {
        ToggleLite.setAll(mapOf("a" to true, "b" to false, "c" to true))

        assertTrue(ToggleLite.isEnabled("a"))
        assertFalse(ToggleLite.isEnabled("b"))
        assertTrue(ToggleLite.isEnabled("c"))
    }

    @Test
    fun `getAll returns copy of all flags`() {
        ToggleLite.set("x", true)
        ToggleLite.set("y", false)

        val all = ToggleLite.getAll()

        assertEquals(mapOf("x" to true, "y" to false), all)
    }

    @Test
    fun `clear removes all flags and listeners`() {
        ToggleLite.set("feature", true)
        var called = false
        ToggleLite.observe("feature") { called = true }

        ToggleLite.clear()

        assertFalse(ToggleLite.isEnabled("feature"))
        ToggleLite.set("feature", true)
        assertFalse(called)
    }

    @Test
    fun `whenEnabled executes block only when enabled`() {
        ToggleLite.set("feature", true)
        val result = ToggleLite.whenEnabled("feature") { "executed" }
        assertEquals("executed", result)

        ToggleLite.set("feature", false)
        val result2 = ToggleLite.whenEnabled("feature") { "executed" }
        assertNull(result2)
    }
}
