/*
We need a reusable program to encode/decode text to unicode value as represented in Java and vice-versa.

Encoder:
Input
"hola"

Output
"\u0068\u006f\u006c\u0061"

Decoder:
Input
"\u0068\u006f\u006c\u0061"

Output
"hola"
 */

fun main() {
    assertEquals("hola", Java_format_Unicode_decoder("\\u0068\\u006f\\u006c\\u0061"))
    assertEquals("\\u0068\\u006f\\u006c\\u0061", Java_format_Unicode_encoder("hola"))
    
}

fun Java_format_Unicode_encoder(input: String?): String {
    val str = input ?: ""
    return str.map {
        "\\u" + it.code.toString(16).padStart(4,'0')
    }.joinToString("")}

fun Java_format_Unicode_decoder(input: String?): String {
    val txt = input?: ""
    val r = txt.split("\\u")
    if(r.size<2) return txt
    val ret = r[0] + (1..r.lastIndex).map {ind->
        val n = r[ind].take(4)
        val d = n.toIntOrNull(16)
        if(d==null) r[ind] else "" + d.toChar() + r[ind].drop(4)
    }.joinToString("")
    return ret
}