
/*
Pedro's Urns
Pedro has n urns, numbered from 0 to n-1. Each urn i contains r_i red balls and b_i black balls.

Pedro removes a random ball from urn i = 0 and places it in urn i = 1. For the next step, he removes a random ball from urn i = 1 and places it inside urn i = 2. He repeats this process until he reaches the final i = n-1 urn.

Your task is to return the probability that Pedro's last ball is red.

Example
You will receive the number of urns and two arrays, one indicating the number of black balls in each i-th urn and the other, indicating the number of red balls in each i-th urn, where 0 <= i <= n-1 is the array index.

n = 2, b = [9, 4], r = [1, 5]
In the above example, there are 9 black balls and 1 red ball in urn i=0 and 4 black balls and 5 red balls in urn i=1.

Pedro has 10% chance of picking a red ball and 90% chance of picking a black ball in urn 0.
After placing his chosen ball in the last urn, 1, he has 10% * 60% + 90% * 50% = 51% chance of getting a red ball and 10% * 40% + 90% * 50% = 49% chance of picking a black ball.
Therefore, for this example, the chance that Pedro retrieves a red ball from the last urn is 51%

 */

fun main() {
    assertEquals(0.6666666666666667, Pedros_Urns(1, intArrayOf(10), intArrayOf(20)))
    assertEquals(0.51, Pedros_Urns(2, intArrayOf(9, 4), intArrayOf(1, 5)))
    assertEquals(0.5, Pedros_Urns(2, intArrayOf(2, 2), intArrayOf(2, 2)))
}

fun Pedros_Urns(n: Int, b: IntArray, r: IntArray): Double {
    
    var blue_prob = 0.0
    var red_prob = 0.0
    b.zip(r) {blue,red->
        val new_blue_count = blue + blue_prob
        val new_red_count = red + red_prob
        blue_prob = new_blue_count/(new_blue_count+new_red_count)
        red_prob = 1.0 - blue_prob
    }
    return red_prob
    
}