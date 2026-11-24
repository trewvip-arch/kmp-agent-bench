package bench

import kotlin.test.Test
import kotlin.test.assertEquals

class DurationTest {
    @Test
    fun negativeBecomesZero() {
        assertEquals("0ms", formatDuration(-5L))
        assertEquals("0ms", formatDuration(Long.MIN_VALUE))
    }

    @Test
    fun zeroIsMilliseconds() {
        assertEquals("0ms", formatDuration(0L))
    }

    @Test
    fun subSecondUsesMilliseconds() {
        assertEquals("1ms", formatDuration(1L))
        assertEquals("850ms", formatDuration(850L))
        assertEquals("999ms", formatDuration(999L))
    }

    @Test
    fun subMinuteUsesOneDecimalSecond() {
        assertEquals("1.0s", formatDuration(1_000L))
        assertEquals("12.3s", formatDuration(12_300L))
        assertEquals("12.3s", formatDuration(12_399L))
        assertEquals("59.9s", formatDuration(59_999L))
    }

    @Test
    fun exactMinutePadsSeconds() {
        assertEquals("1m00s", formatDuration(60_000L))
    }

    @Test
    fun overMinuteKeepsRemainingSeconds() {
        assertEquals("1m05s", formatDuration(65_000L))
        assertEquals("2m30s", formatDuration(150_000L))
        assertEquals("59m59s", formatDuration(3_599_999L))
    }
}
