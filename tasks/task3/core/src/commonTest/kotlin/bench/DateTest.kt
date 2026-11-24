package bench

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Clock

class DateTest {
    @Test
    fun todayUtcHasIsoDateFormat() {
        assertTrue(todayUtc().matches(Regex("\\d{4}-\\d{2}-\\d{2}")))
    }

    @Test
    fun todayUtcMatchesCurrentUtcDate() {
        val expected = Clock.System.now().toLocalDateTime(TimeZone.UTC).date.toString()
        assertEquals(expected, todayUtc())
    }
}
