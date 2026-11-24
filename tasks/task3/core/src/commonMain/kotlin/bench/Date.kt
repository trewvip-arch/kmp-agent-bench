package bench

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

/** 返回当前 UTC 日期，格式为 yyyy-MM-dd。 */
fun todayUtc(): String =
    Clock.System.now()
        .toLocalDateTime(TimeZone.UTC)
        .date
        .toString()
