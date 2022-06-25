/*
The task is to write a function which formats a duration, given as a number of seconds, in a human-friendly way.

The function must accept a non-negative integer. If it is zero, it just returns "now". Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds.

It is much easier to understand with an example:

* For seconds = 62, your function should return
    "1 minute and 2 seconds"
* For seconds = 3662, your function should return
    "1 hour, 1 minute and 2 seconds"
 */

fun main() {
    assertEquals("1 second", Human_readable_duration_format(1))
    assertEquals("1 minute and 2 seconds", Human_readable_duration_format(62))
    assertEquals("2 minutes", Human_readable_duration_format(120))
    assertEquals("1 hour", Human_readable_duration_format(3600))
    assertEquals("1 hour, 1 minute and 2 seconds", Human_readable_duration_format(3662))
    assertEquals("6 years, 192 days, 13 hours, 3 minutes and 54 seconds",
        Human_readable_duration_format(205851834)
    )
}

fun Human_readable_duration_format(seconds: Int): String {
    if(seconds==0) return "now"
    val periods = mapOf("year" to 31536000, "day" to 86400, "hour" to 3600, "minute" to 60, "second" to 1)
    var sec = seconds
    val ret = mutableListOf<String>()
    
    periods.forEach {k,v->
        if(sec>=v) {
            val qty = sec/v
            val ending = if(qty>1) "s" else ""
            ret.add("$qty $k$ending")
            sec %= v
        }
    }
    if(ret.size<=1) return ret.joinToString("")
    return ret.dropLast(1).joinToString(", ")+" and "+ret.last()
}