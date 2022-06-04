/*
A format for expressing an ordered list of integers is to use a comma separated list of either

individual integers
or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'. The range includes all integers in the interval including both endpoints. It is not considered a range unless it spans at least 3 numbers. For example "12,13,15-17"
Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.

Example:

solution([-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20]);
// returns "-10--8,-6,-3-1,3-5,7-11,14,15,17-20"
Courtesy of rosettacode.org
 */


fun main() {
    assertEquals("-6,-3-1,3-5,7-11,14,15,17-20", Range_Extraction(intArrayOf(-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20)))
    assertEquals("-3--1,2,10,15,16,18-20", Range_Extraction(intArrayOf(-3, -2, -1, 2, 10, 15, 16, 18, 19, 20)))
}

fun Range_Extraction(arr: IntArray): String {
    val l = arr.sorted().toMutableList()
    (0..1).forEach{l+=l.first()}
    var ret = ""
    var index = 0
    while(index < arr.size) {
        if(ret.length>0) ret += ","
        if(l[index+2] - l[index] == 2) {
            ret += "${l[index]}"
            do index++
            while(l[index+1] - l[index] == 1 && index < arr.size)
            ret += "-${l[index]}"
        } else {
            ret += "${l[index]}"
        }
        index++
    }
    return ret
}