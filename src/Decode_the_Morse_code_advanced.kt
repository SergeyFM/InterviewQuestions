/*
You have to write a Morse code decoder for wired electrical telegraph.
Electric telegraph is operated on a 2-wire line with a key that, when pressed, connects the wires together, which can be detected on a remote station. The Morse code encodes every character being transmitted as a sequence of "dots" (short presses on the key) and "dashes" (long presses on the key).

When transmitting the Morse code, the international standard specifies that:

"Dot" – is 1 time unit long.
"Dash" – is 3 time units long.
Pause between dots and dashes in a character – is 1 time unit long.
Pause between characters inside a word – is 3 time units long.
Pause between words – is 7 time units long.
However, the standard does not specify how long that "time unit" is. And in fact different operators would transmit at different speed. An amateur person may need a few seconds to transmit a single character, a skilled professional can transmit 60 words per minute, and robotic transmitters may go way faster.

For this kata we assume the message receiving is performed automatically by the hardware that checks the line periodically, and if the line is connected (the key at the remote station is down), 1 is recorded, and if the line is not connected (remote key is up), 0 is recorded. After the message is fully received, it gets to you for decoding as a string containing only symbols 0 and 1.

For example, the message HEY JUDE, that is ···· · −·−−   ·−−− ··− −·· · may be received as follows:

1100110011001100000011000000111111001100111111001111110000000000000011001111110011111100111111000000110011001111110000001111110011001100000011

As you may see, this transmission is perfectly accurate according to the standard, and the hardware sampled the line exactly two times per "dot".

That said, your task is to implement two functions:

1. Function decodeBits(bits), that should find out the transmission rate of the message, correctly decode the message to dots ., dashes - and spaces (one between characters, three between words) and return those as a string. Note that some extra 0's may naturally occur at the beginning and the end of a message, make sure to ignore them. Also if you have trouble discerning if the particular sequence of 1's is a dot or a dash, assume it's a dot.
2. Function decodeMorse(morseCode), that would take the output of the previous function and return a human-readable string.
 */

fun main() {
    assertEquals(
        "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG.",
        Decode_the_Morse_code_advanced_decodeMorse(Decode_the_Morse_code_advanced_decodeBits("00011100010101010001000000011101110101110001010111000101000111010111010001110101110000000111010101000101110100011101110111000101110111000111010000000101011101000111011101110001110101011100000001011101110111000101011100011101110001011101110100010101000000011101110111000101010111000100010111010000000111000101010100010000000101110101000101110001110111010100011101011101110000000111010100011101110111000111011101000101110101110101110"))
    )
    assertEquals(
        "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG.",
        Decode_the_Morse_code_advanced_decodeMorse(Decode_the_Morse_code_advanced_decodeBits("11111111111111100000000000000011111000001111100000111110000011111000000000000000111110000000000000000000000000000000000011111111111111100000111111111111111000001111100000111111111111111000000000000000111110000011111000001111111111111110000000000000001111100000111110000000000000001111111111111110000011111000001111111111111110000011111000000000000000111111111111111000001111100000111111111111111000000000000000000000000000000000001111111111111110000011111000001111100000111110000000000000001111100000111111111111111000001111100000000000000011111111111111100000111111111111111000001111111111111110000000000000001111100000111111111111111000001111111111111110000000000000001111111111111110000011111000000000000000000000000000000000001111100000111110000011111111111111100000111110000000000000001111111111111110000011111111111111100000111111111111111000000000000000111111111111111000001111100000111110000011111111111111100000000000000000000000000000000000111110000011111111111111100000111111111111111000001111111111111110000000000000001111100000111110000011111111111111100000000000000011111111111111100000111111111111111000000000000000111110000011111111111111100000111111111111111000001111100000000000000011111000001111100000111110000000000000000000000000000000000011111111111111100000111111111111111000001111111111111110000000000000001111100000111110000011111000001111111111111110000000000000001111100000000000000011111000001111111111111110000011111000000000000000000000000000000000001111111111111110000000000000001111100000111110000011111000001111100000000000000011111000000000000000000000000000000000001111100000111111111111111000001111100000111110000000000000001111100000111111111111111000000000000000111111111111111000001111111111111110000011111000001111100000000000000011111111111111100000111110000011111111111111100000111111111111111000000000000000000000000000000000001111111111111110000011111000001111100000000000000011111111111111100000111111111111111000001111111111111110000000000000001111111111111110000011111111111111100000111110000000000000001111100000111111111111111000001111100000111111111111111000001111100000111111111111111"))
    )
}

fun Decode_the_Morse_code_advanced_decodeBits(bits: String): String {
    
    var indexer = 0
    var unit = 256
    var space_indexer = 0
    var space_unit = 256
    (bits.take(256).trim('0')+" ").forEach {
        if (it == '1') {
            indexer++
        } else {
            if (indexer > 0 && indexer < unit) unit = indexer
            indexer = 0
        }
        if (it == '0') {
            space_indexer++
        } else {
            if (space_indexer > 0 && space_indexer < space_unit) space_unit = space_indexer
            space_indexer = 0
        }
    }
    
    if (space_unit < unit && unit%3==0) unit = space_unit
    
    val dash = "1".repeat(unit*3)
    val dot = "1".repeat(unit)
    val space = "0".repeat(unit)
    
    var ret = bits
    ret = ret.replace(dash,"−")
    ret = ret.replace(dot,"·")
    ret = ret.replace(space," ")
    ret = ret.replace("0","")
    
    return ret
}

fun Decode_the_Morse_code_advanced_decodeMorse(code: String): String {
    
    var ret = code.split("       ").map { word ->
        word.split("   ").map { c ->
            val sym = c.replace(" ","")
            codes[sym] ?: " "
        }.joinToString("")
    }.joinToString(" ").trim()
    
    return ret
}

val codes = mapOf<String, String>(
    "·−" to "A",
    "−···" to "B",
    "−·−·" to "C",
    "−··" to "D",
    "·" to "E",
    "··−·" to "F",
    "−−·" to "G",
    "····" to "H",
    "··" to "I",
    "·−−−" to "J",
    "−·−" to "K",
    "·−··" to "L",
    "−−" to "M",
    "−·" to "N",
    "−−−" to "O",
    "·−−·" to "P",
    "−−·−" to "Q",
    "·−·" to "R",
    "···" to "S",
    "−" to "T",
    "··−" to "U",
    "···−" to "V",
    "·−−" to "W",
    "−··−" to "X",
    "−·−−" to "Y",
    "−−··" to "Z",
    "·−−−−" to "1",
    "··−−−" to "2",
    "···−−" to "3",
    "····−" to "4",
    "·····" to "5",
    "−····" to "6",
    "−−···" to "7",
    "−−−··" to "8",
    "−−−−·" to "9",
    "−−−−−" to "0",
    "·−·−·−" to ".",
    "−−··−−" to ",",
    "−−−···" to ".",
    "··−−··" to "?",
    "−·−·−−" to "!",
    "−····−" to "''",
    "−··−·" to "/",
    "−·−−·−" to "()",
    "·−−·−·" to "@",
    "−···−" to "=",
    "···−−−···" to "SOS"
)