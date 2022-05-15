/*
The task is to implement what I call Iterative Rotation Cipher (IRC). To complete the task, you will create an object with two methods, encode and decode. (For non-JavaScript versions, you only need to write the two functions without the enclosing dict)

Input
The encode method will receive two arguments — a positive integer n and a string value.

The decode method will receive one argument — a string value.

Output
Each method will return a string value.

How It Works
Encoding and decoding are done by performing a series of character and substring rotations on a string input.

Encoding: The number of rotations is determined by the value of n. The sequence of rotations is applied in the following order:
 Step 1: remove all spaces in the string (but remember their positions)
 Step 2: shift the order of characters in the new string to the right by n characters
 Step 3: put the spaces back in their original positions
 Step 4: shift the characters of each substring (separated by one or more consecutive spaces) to the right by n
Repeat this process until it has been completed n times in total.
The value n is then prepended to the resulting string with a space.

Decoding: Decoding simply reverses the encoding process.
 */

fun main() {
    assertEquals("52 eha texcpaet ain ptu ie csroe et othpbuu ?okq eTi s,anirepewhpa soy asopt ht ourg nd arh rc bnapdeen lelriv my n eateble esone",IterativeRotationCipher.encode(52,"The question is, are we happy to suppose that our grandchildren may never be able to see an elephant except in a picture book?"))
    assertEquals("The question is, are we happy to suppose that our grandchildren may never be able to see an elephant except in a picture book?",IterativeRotationCipher.decode("52 eha texcpaet ain ptu ie csroe et othpbuu ?okq eTi s,anirepewhpa soy asopt ht ourg nd arh rc bnapdeen lelriv my n eateble esone"))
}

object IterativeRotationCipher {
    
    fun decode(s:String): String {
        val n = s.takeWhile{it.isDigit()}.toIntOrNull()?:0
        var code = s.drop("$n ".length)
        println("DECODE $n $code")
        (1..n).forEach {code = decode_once(n,code)}
        return code
    }
    
    fun decode_once(n: Int, s: String): String {
        val step3 = s.split(" ").map {
            shiftLeft(n,it)
        }.joinToString(" ")
        val spaces = step3.mapIndexedNotNull{ind,c-> if(c==' ') ind else null}
        var step2 = shiftLeft(n,step3.filter{it!=' '})
        var ptr = 0
        return (0..s.lastIndex).map{ind->
            if(ind in spaces) " "
            else {val c = step2.take(1); step2 = step2.drop(1); c }
        }.joinToString("")
    }
    
    private fun shiftLeft(n: Int, what: String): String {
        if(n<=0 || what.length==0) return what
        val shift = n%what.length
        return  what.drop(shift) + what.take(shift)
    }
    
    fun encode(n: Int, s: String): String {
        println("ENCODE $n $s")
        var code = s
        (1..n).forEach {code = encode_once(n,code)}
        return "$n $code"
    }
    
    private fun encode_once(n: Int, s: String): String {
        val spaces = s.mapIndexedNotNull{ind,c-> if(c==' ') ind else null}
        var step2 = shiftRight(n,s.filter{it!=' '})
        var ptr = 0
        val step3 = (0..s.lastIndex).map{ind->
            if(ind in spaces) " "
            else {val c = step2.take(1); step2 = step2.drop(1); c }
        }.joinToString("")
        return step3.split(" ").map{
            shiftRight(n,it)
        }.joinToString(" ")
    }
    
    private fun shiftRight(n: Int, what: String): String {
        if(n<=0 || what.length==0) return what
        val shift = n%what.length
        return what.takeLast(shift) + what.dropLast(shift)
    }
}