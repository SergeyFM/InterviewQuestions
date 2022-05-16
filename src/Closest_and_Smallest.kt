/*
Input
a string strng of n positive numbers (n = 0 or n >= 2)
Let us call weight of a number the sum of its digits. For example 99 will have "weight" 18, 100 will have "weight" 1.

Two numbers are "close" if the difference of their weights is small.

Task:
For each number in strng calculate its "weight" and then find two numbers of strng that have:

the smallest difference of weights ie that are the closest
with the smallest weights
and with the smallest indices (or ranks, numbered from 0) in strng
Output:
an array of two arrays, each subarray in the following format:
[number-weight, index in strng of the corresponding number, original corresponding number in strng]

or a pair of two subarrays (Haskell, Clojure, FSharp) or an array of tuples (Elixir, C++)

or a (char*) in C or a string in some other languages mimicking an array of two subarrays or a string

or a matrix in R (2 rows, 3 columns, no columns names)

The two subarrays are sorted in ascending order by their number weights if these weights are different, by their indexes in the string if they have the same weights.
 */

fun main() {
    var s = "202174 186 177039 94 189002 66 94235 112 326314 66 48"
    var r = arrayOf(intArrayOf(12, 5, 66), intArrayOf(12, 9, 66))
    assertEquals(r.map{it.joinToString(" ")}, Closest_and_Smallest(s).map{it.joinToString(" ")})
    s = "294748 92 236912 86 185687 30 233059 2 87792 154 8"
    r = arrayOf(intArrayOf(2, 7, 2), intArrayOf(3, 5, 30))
    assertEquals(r.map{it.joinToString(" ")}, Closest_and_Smallest(s).map{it.joinToString(" ")})
    s = "220489 190 161764 5 484420 14 181644 173 252343 86 35"
    r = arrayOf(intArrayOf(5, 3, 5), intArrayOf(5, 5, 14))
    assertEquals(r.map{it.joinToString(" ")}, Closest_and_Smallest(s).map{it.joinToString(" ")})
    s = "466874 170 333771 177 44791 15 80369 166 361158 116 16"
    r = arrayOf(intArrayOf(8, 1, 170), intArrayOf(8, 9, 116))
    assertEquals(r.map{it.joinToString(" ")}, Closest_and_Smallest(s).map{it.joinToString(" ")})
    s = "171595 73 449051 66 243548 56 24997 76 240264 86 17"
    r = arrayOf(intArrayOf(10, 1, 73), intArrayOf(11, 5, 56))
    assertEquals(r.map{it.joinToString(" ")}, Closest_and_Smallest(s).map{it.joinToString(" ")})
    s = "22 22 13 13 10 10 0 0 1 1"
    r = arrayOf(intArrayOf(0, 6, 0), intArrayOf(0, 7, 0))
    assertEquals(r.map{it.joinToString(" ")}, Closest_and_Smallest(s).map{it.joinToString(" ")})
    s = "22 22 13 13 10 10 1 1"
    r = arrayOf(intArrayOf(1, 4, 10), intArrayOf(1, 5, 10))
    assertEquals(r.map{it.joinToString(" ")}, Closest_and_Smallest(s).map{it.joinToString(" ")})
}

fun Closest_and_Smallest(strng: String): Array<IntArray> {
    val num_txt = strng.split(" ").filter{it.trim().length>0}
    val weights = num_txt.mapIndexed {ind,str->
        Pair( str.mapNotNull {c-> c.digitToIntOrNull()}.sum(), ind )
    }
    val comparator = compareBy<Pair<Int,Int>>{it.first}.thenBy{it.second}
    val sorted = weights.sortedWith(comparator)
    
    val laureats = sorted.windowed(2).minByOrNull{it[1].first-it[0].first} ?: listOf(Pair(0,0))
    
    return laureats.map{
        intArrayOf(it.first,it.second,num_txt[it.second].toInt())
    }.toTypedArray()
}