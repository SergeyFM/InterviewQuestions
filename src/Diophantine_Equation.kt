/*
In mathematics, a Diophantine equation is a polynomial equation, usually with two or more unknowns, such that only the integer solutions are sought or studied.

In this kata we want to find all integers x, y (x >= 0, y >= 0) solutions of a diophantine equation of the form:

x2 - 4 * y2 = n
(where the unknowns are x and y, and n is a given positive number) in decreasing order of the positive xi.

If there is no solution return [] or "[]" or "".

Examples:
solEquaStr(90005) --> "[[45003, 22501], [9003, 4499], [981, 467], [309, 37]]"
solEquaStr(90002) --> "[]"
Hint:
x2 - 4 * y2 = (x - 2*y) * (x + 2*y)
 */

fun main() {
        assertEquals("[[4500001, 2250000], [73801, 36870]]", Diophantine_Equation(9000001))
        assertEquals("[[2250002, 1125000], [173090, 86532], [132370, 66168], [10402, 4980]]", Diophantine_Equation(9000004))
        assertEquals("[[4500003, 2250001], [900003, 449999], [642861, 321427], [155187, 77579], [128589, 64277], [31107, 15481], [22269, 11033], [4941, 1963]]", Diophantine_Equation(9000005))
        assertEquals("[[45000001, 22500000], [6428575, 3214284], [3461545, 1730766], [494551, 247230]]", Diophantine_Equation(90000001))
        assertEquals("[[22500002, 11250000], [252898, 126360], [93602, 46560], [22498, 10200]]", Diophantine_Equation(90000004))
}


fun Diophantine_Equation(n:Long) = mutableListOf<Pair<Long,Long>>().apply {
    (n downTo 0).forEach {y->
        val x = Math.sqrt((n+4L*(y*y)).toDouble())
        if(x%1.0==0.0) add(Pair(x.toLong(),y))
    }
}.map{"[${it.first}, ${it.second}]"}.joinToString(", ","[","]")