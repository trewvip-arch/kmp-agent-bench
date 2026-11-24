package bench

/** 把标题转成 URL slug：小写、空格换连字符、去掉多余连字符。 */
public fun slugify(input: String): String =
    input.trim()
        .lowercase()
        .replace(Regex("\\s+"), "-")
        .replace(Regex("-+"), "-")
        .trim('-')

/** 统计一段文本里单词的个数，连续空白算一个分隔。 */
public fun wordCount(input: String): Int =
    input.split(Regex("\\s+")).count { it.isNotBlank() }
