/*
Given an array X of positive integers, its elements are to be transformed by running the following operation on them as many times as required:

if X[i] > X[j] then X[i] = X[i] - X[j]

When no more transformations are possible, return its sum ("smallest possible sum").

For instance, the successive transformation of the elements of input X = [6, 9, 21] is detailed below:

X_1 = [6, 9, 12] # -> X_1[2] = X[2] - X[1] = 21 - 9
X_2 = [6, 9, 6]  # -> X_2[2] = X_1[2] - X_1[0] = 12 - 6
X_3 = [6, 3, 6]  # -> X_3[1] = X_2[1] - X_2[0] = 9 - 6
X_4 = [6, 3, 3]  # -> X_4[2] = X_3[2] - X_3[1] = 6 - 3
X_5 = [3, 3, 3]  # -> X_5[1] = X_4[0] - X_4[1] = 6 - 3
The returning output is the sum of the final transformation (here 9).

Example
solution([6, 9, 21]) #-> 9
Solution steps:
[6, 9, 12] #-> X[2] = 21 - 9
[6, 9, 6] #-> X[2] = 12 - 6
[6, 3, 6] #-> X[1] = 9 - 6
[6, 3, 3] #-> X[2] = 6 - 3
[3, 3, 3] #-> X[1] = 6 - 3
 */


fun main() {
    assertEquals(9, Smallest_possible_sum(longArrayOf(6,9,21)))
    assertEquals(3, Smallest_possible_sum(longArrayOf(1,21,55)))
    assertEquals(5, Smallest_possible_sum(longArrayOf(3,13,23,7,83)))
    assertEquals(923, Smallest_possible_sum(longArrayOf(71,71,71,71,71,71,71,71,71,71,71,71,71)))
    assertEquals(22, Smallest_possible_sum(longArrayOf(11,22)))
    assertEquals(2, Smallest_possible_sum(longArrayOf(5,17)))
    assertEquals(12, Smallest_possible_sum(longArrayOf(4,16,24)))
    assertEquals(9, Smallest_possible_sum(longArrayOf(9)))
}


fun Smallest_possible_sum(numbers: LongArray): Long {
    
    if (numbers.isEmpty()) return 0L
    val numbers_sorted = numbers.sorted()
    var divisor = numbers_sorted[0]
    val qty = numbers_sorted.size
    
    (0..100000).forEach {
        for ((i,n) in numbers_sorted.withIndex()) {
            val diff = n%divisor
            if (diff > 0) {
                divisor = diff
                break
            }
            if (i == qty-1) return divisor*qty
        }
    }
    return -1L
}