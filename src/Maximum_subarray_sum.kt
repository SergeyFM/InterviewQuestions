/*
The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence in an array or list of integers
 */

fun main() {
    assertEquals(0, Maximum_subarray_sum(emptyList()))
    assertEquals(6, Maximum_subarray_sum(listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)))
    assertEquals(0, Maximum_subarray_sum(listOf(-2, -1, -3, -4, -1, -2, -1, -5, -4)))
    assertEquals(155, Maximum_subarray_sum(listOf(7, 4, 11, -11, 39, 36, 10, -6, 37, -10, -32, 44, -26, -34, 43, 43)))
    
}


fun Maximum_subarray_sum(arr: List<Int>): Int {
    // Kadane's algorithm.
    // Finds the maximum sum of a contiguous subsequence in an array or list of integers.
    var sum = 0
    var max = 0
    arr.forEach {v->
        sum = if(v>sum+v) v else sum+v
        max = if(sum>max) sum else max
    }
return max
}