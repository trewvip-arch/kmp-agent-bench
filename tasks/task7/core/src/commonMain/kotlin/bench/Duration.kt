package bench

/**
 * 把毫秒时长格式化成便于展示的文本。
 *
 * - 小于 1000ms：`850ms`
 * - 小于 60 秒：`12.3s`（一位小数，截断不四舍五入）
 * - 60 秒及以上：`1m05s`（秒补两位）
 * - 负数按 0 处理
 */
public expect fun formatDuration(ms: Long): String

/** 把分、秒拼接成 `1m05s` 形式，供各平台实现复用。 */
internal fun formatMinutesSeconds(minutes: Long, seconds: Long): String =
    "${minutes}m${seconds.toString().padStart(2, '0')}s"
