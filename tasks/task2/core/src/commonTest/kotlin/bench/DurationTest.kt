package bench

import kotlin.test.Test
import kotlin.test.assertEquals

class DurationTest {
    @Test
    fun negativeIsTreatedAsZero() {
        assertEquals("0ms", formatDuration(-1L))
        assertEquals("0ms", formatDuration(Long.MIN_VALUE))
    }

    @Test
    fun lessThanOneSecondUsesMillis() {
        assertEquals("0ms", formatDuration(0L))
        assertEquals("850ms", formatDuration(850L))
        assertEquals("999ms", formatDuration(999L))
    }

    @Test
    fun lessThanOneMinuteUsesOneDecimal() {
        assertEquals("1.0s", formatDuration(1_000L))
        assertEquals("12.3s", formatDuration(12_300L))
        assertEquals("59.9s", formatDuration(59_900L))
    }

    @Test
    fun oneMinuteOrMoreUsesMinutesAndPaddedSeconds() {
        assertEquals("1m00s", formatDuration(60_000L))
        assertEquals("1m05s", formatDuration(65_000L))
        assertEquals("1m05s", formatDuration(65_999L))
        assertEquals("2m05s", formatDuration(125_000L))
    }
}
