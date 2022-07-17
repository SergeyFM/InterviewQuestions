/*
Your job is to write a function which increments a string, to create a new string.

If the string already ends with a number, the number should be incremented by 1.
If the string does not end with a number. the number 1 should be appended to the new string.

Examples:

foo -> foo1

foobar23 -> foobar24

foo0042 -> foo0043

foo9 -> foo10

foo099 -> foo100

Attention: If the number has leading zeros the amount of digits should be considered.
 */

fun main() {
    assertEquals(String_incrementer(""), "1")
    assertEquals(String_incrementer("010"), "011")
    assertEquals(String_incrementer("999"), "1000")
    assertEquals(String_incrementer("foo0042"), "foo0043")
    
}

fun String_incrementer (str: String) : String {
// Increments the number at the end of the str
    
    //extract a base and a number
    var number: String = ""
    for (c: Char in str.reversed()) {
        if (c in '0'..'9') number = c + number
        else break
    }
    val base: String = str.take(str.length-number.length)
    
    //no number was extracted
    if (number == "") return str + '1'
    
    //number++ not reducing length
    var number_int: Int = number.toInt()
    number = (++number_int).toString().padStart(number.length,'0')
    
    //return
    return base + number
}