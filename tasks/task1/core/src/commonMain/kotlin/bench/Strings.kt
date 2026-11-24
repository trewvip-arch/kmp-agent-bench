package bench

import kotlin.text.Regex

/** 复用的空白分隔符。 */
internal val WHITESPACE_REGEX = Regex("\\s+")

/** 统一大小写并去掉首尾空白。 */
internal fun normalize(input: String?): String = input?.trim()?.lowercase().orEmpty()
