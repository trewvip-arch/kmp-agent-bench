package bench

import kotlin.test.Test
import kotlin.test.assertEquals

class DurationAcceptanceTest {
    @Test fun subSecond() = assertEquals("850ms", formatDuration(850L))
    @Test fun subMinute() = assertEquals("12.3s", formatDuration(12_300L))
    @Test fun overMinute() = assertEquals("1m05s", formatDuration(65_000L))
    @Test fun exactMinute() = assertEquals("1m00s", formatDuration(60_000L))
    @Test fun negative() = assertEquals("0ms", formatDuration(-5L))
}
