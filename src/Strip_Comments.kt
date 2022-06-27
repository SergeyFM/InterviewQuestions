/*
Complete the solution so that it strips all text that follows any of a set of comment markers passed in. Any whitespace at the end of the line should also be stripped out.

Example:

Given an input string of:

apples, pears # and bananas
grapes
bananas !apples
The output expected would be:

apples, pears
grapes
bananas
 */

fun main() {
    assertEquals("apples, plums\npears\noranges", Strip_Comments("apples, plums % and bananas\npears\noranges !applesauce", charArrayOf('%', '!')))
    assertEquals("Q\nu\ne", Strip_Comments("Q @b\nu\ne -e f g", charArrayOf('@', '-')))
}

fun Strip_Comments(input: String, markers: CharArray): String {
    var ret: String = ""
    var lines = input.lines()
    for (l: String in lines) {
        if (ret.length > 0) ret += '\n'
        for (c: Char in l) {
            if (c in markers) {
                ret = ret.trimEnd()
                break
            }
            ret += c
        }
    }
    return ret
}

fun Strip_Comments_better(input: String, markers: CharArray): String =
    input.lines().map {line->
        line.split(*markers).first().trimEnd()
    }.joinToString("\n")