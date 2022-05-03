/*
The Fibonacci numbers are the numbers in the following integer sequence (Fn):
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, ...

such as
F(n) = F(n-1) + F(n-2) with F(0) = 0 and F(1) = 1.

Given a number, say prod (for product), we search two Fibonacci numbers F(n) and F(n+1) verifying
F(n) * F(n+1) = prod.

Your function productFib takes an integer (prod) and returns an array:

[F(n), F(n+1), true] or {F(n), F(n+1), 1} or (F(n), F(n+1), True)
depending on the language if F(n) * F(n+1) = prod.

If you don't find two consecutive F(n) verifying F(n) * F(n+1) = prod

you will return
[F(n), F(n+1), false] or {F(n), F(n+1), 0} or (F(n), F(n+1), False)

F(n) being the smallest one such as F(n) * F(n+1) > prod.

 */

fun main() {
    var r = longArrayOf(55, 89, 1)
    assertEquals(r, Product_of_consecutive_Fib_numbers(4895))
    r = longArrayOf(89, 144, 0)
    assertEquals(r, Product_of_consecutive_Fib_numbers(5895))
    r = longArrayOf(6765, 10946, 1)
    assertEquals(r, Product_of_consecutive_Fib_numbers(74049690))
    r = longArrayOf(10946, 17711, 0)
    assertEquals(r, Product_of_consecutive_Fib_numbers(84049690))
    r = longArrayOf(10946, 17711, 1)
    assertEquals(r, Product_of_consecutive_Fib_numbers(193864606))
    r = longArrayOf(610, 987, 0)
    assertEquals(r, Product_of_consecutive_Fib_numbers(447577))

}

fun Product_of_consecutive_Fib_numbers(prod: Long): LongArray {
    var (a,b) = arrayOf(0L,1L)
    while(a*b<prod) {
        a = b.also{b=a}
        b += a
    }
    return longArrayOf(a,b,
        if(prod==a*b) 1L else 0L
    )
}