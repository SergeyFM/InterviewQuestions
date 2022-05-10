import kotlin.math.roundToInt

/*
In this kata, your task is to implement what I call Interlaced Spiral Cipher (ISC).

Encoding a string using ISC is achieved with the following steps:

Form a square large enough to fit all the string characters
Starting with the top-left corner, place string characters in the corner cells moving in a clockwise direction
After the first cycle is complete, continue placing characters in the cells following the last one in its respective row/column
When the outer cells are filled, repeat steps 2 through 4 for the remaining inner squares (refer to the example below for further clarification)
Fill up any unused cells with a space character and return the rows joined together.
Input

Arguments passed to the encode method will never have any trailing spaces.

Output
The encode method should return the encoded message as a string

The decode method should return the decoded message as a string with no trailing spaces
 */

fun main() {
    val str = "If life seems jolly rotten there's something you've forgotten, and that's to laugh and smile and dance and sing. When you're feeling in the dumps, don't be silly chumps. Just purse your lips and whistle, that's the thing!"
    val encoded = "Iisslreh'oh 'ffgonnhs gnm  ctf ani nu l tdd,eunpso syu.s,nd gd pu rpnhrnWalte utlt'heyb  ossgswth!ih i'lneaehat   ss fhetuelitg nedmidstoo u   a,i iim 'etes  losthaiot.d Jpcle' udymasmenneryhg ev e anl  att  tolreone r tyjee "
    assertEquals(str,Interlaced_Spiral_Cipher_decode(encoded))
    assertEquals(encoded, Interlaced_Spiral_Cipher_encode(str))
}


fun Interlaced_Spiral_Cipher_encode(s : String) : String {
    
    val input = s.trim()
    val side = getSideSize(input)
    println ("ENCODE $s --> $side")
    if (side == 0) return ""
    
    val output = CharArray(side*side){' '}
    input.forEachIndexed { ind,c ->
        val ptr = placer(side,ind,'E')
        output[ptr] = c
    }
    val ret = output.joinToString("")
    
    println ("ENCODED: [$ret] \n")
    return ret
    
}


fun Interlaced_Spiral_Cipher_decode(s : String): String {
    
    val input = s.trim()
    val side = getSideSize(input)
    println ("DECODE $s --> $side")
    if (side == 0) return ""
    
    val output = CharArray(side*side){' '}
    input.forEachIndexed { ind,c ->
        val ptr = placer(side,ind,'D')
        output[ptr] = c
    }
    val ret = output.joinToString("").trim()
    
    println("DECODED: [$ret]")
    return ret
    
}

fun getSideSize(s : String) : Int {
    val str_len = s.length
    if (str_len==0) return 0
    (1..32768).forEach {
        if (it*it >= str_len) return it
    }
    return 0
}


fun placer (side: Int, ind : Int, mode : Char) : Int {
    val place = dicGenerator(side)
    val to = if (mode=='E') place.indexOf(ind) else place[ind]
    return to
}

fun dicGenerator(side : Int) : IntArray {
    val dic = MutableList(side*side){0}
    val layer_number = ((side+0.5)/2.0).roundToInt()
    var start_number = 0
    (0..layer_number-1).forEach { layer ->
        val start_ptr = (side+1)*layer
        val endln_offset = side-1-layer*2
        val start_pointers = arrayOf(start_ptr,start_ptr+endln_offset,(start_ptr+endln_offset)+side*endln_offset,start_ptr+side*endln_offset)
        val jumps = arrayOf(1,side,-1,-side)
        val phases = if (endln_offset>0) 3 else 0
        (0..phases).forEach { phase ->
            dic[start_pointers[phase]] = start_number + phase
            (1..endln_offset - 1).forEach { cell_offset ->
                val ptr = start_pointers[phase] + cell_offset * jumps[phase]
                val num = start_number + phase + cell_offset * 4
                dic[ptr] = num
            }
            
        }
        start_number += endln_offset*4
    }
    return dic.toIntArray()
}