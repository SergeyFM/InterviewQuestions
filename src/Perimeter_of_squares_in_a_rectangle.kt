import java.math.BigInteger

/*
The function perimeter has for parameter n where n + 1 is the number of squares (they are numbered from 0 to n) and returns the total perimeter of all the squares.

Example: There are 6 squares the sides of which have a length of 1, 1, 2, 3, 5, 8.
It's easy to see that the sum of the perimeters of these squares is : 4 * (1 + 1 + 2 + 3 + 5 + 8) = 4 * 20 = 80
 */

fun main() {
    assertEquals(BigInteger.valueOf(80), Perimeter_of_squares_in_a_rectangle(5))
    assertEquals(BigInteger.valueOf(216), Perimeter_of_squares_in_a_rectangle(7))
    assertEquals(BigInteger.valueOf(14098308), Perimeter_of_squares_in_a_rectangle(30))
    var r = BigInteger("6002082144827584333104")
    assertEquals(r, Perimeter_of_squares_in_a_rectangle(100))
    r = BigInteger("2362425027542282167538999091770205712168371625660854753765546783141099308400948230006358531927265833165504")
    assertEquals(r, Perimeter_of_squares_in_a_rectangle(500))
}


fun Perimeter_of_squares_in_a_rectangle(n: Int): BigInteger {
    
    var (sum,b) = arrayOf(BigInteger.ONE,BigInteger.ONE)
    if(n<=0) return BigInteger.ZERO
    
    (1..n+2).forEach {
        b = (sum+b).also{sum=b}
     }
    sum--
    sum *= BigInteger.valueOf(4)
    
    return sum
}