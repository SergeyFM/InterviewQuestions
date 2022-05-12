/*
The task is to write a function that returns the smallest and largest integers in an unsorted string. A range is considered a finite sequence of consecutive integers.

Input
Your function will receive two arguments:
A string comprised of integers in an unknown range; think of this string as the result when a range of integers is shuffled around in random order then joined together into a string
An integer value representing the size of the range
Output
Your function should return the starting (minimum) and ending (maximum) numbers of the range in the form of an array/list comprised of two integers.
 */

fun main() {
    assertEquals(Pair(1,14),Range_of_Integers_in_an_Unsorted_String("6291211413114538107",14))
    assertEquals(Pair(99,116),Range_of_Integers_in_an_Unsorted_String("10610211511099104113100116105103101111114107108112109",18))
    assertEquals(Pair(70,96),Range_of_Integers_in_an_Unsorted_String("937175789196798988958481879092738677708380947485827672",27))
}


fun Range_of_Integers_in_an_Unsorted_String(s: String,n: Int): Pair<Int,Int> {
    val counter = (0..9).map { c ->
        s.filter {it == '0'+c}.count()
    }
    // try all the ranges
    (1..99).forEach { start ->
        val candidate_vals = (start..start+n-1).map{it}
        val candidate_s = candidate_vals.joinToString("")
        val candidate_counter = (0..9).map { c ->
            candidate_s.filter {it == '0'+c}.count()
        }
        //checks
        val enough = candidate_counter.filterIndexed {i,e ->
            e > counter[i]
        }.size == 0
        val equal_len = candidate_s.length == s.length
        val all_numbers_exist = candidate_vals.filter{it.toString() !in s}.size == 0
        
        if (enough && equal_len && all_numbers_exist )
            return Pair(start,start+n-1) // ---> we've found it!
    }
    return Pair(0,0) //--- no luck
}