/*
Create a function that interprets code in the esoteric language Poohbear

The Language
Poohbear is a stack-based language largely inspired by Brainfuck. It has a maximum integer value of 255, and 30,000 cells. The original intention of Poohbear was to be able to send messages that would, to most, be completely indecipherable: Poohbear Wiki

For the purposes of this kata, you will make a version of Poohbear that has infinite memory cells in both directions (so you do not need to limit cells to 30,000)
Cells have a default value of 0
Each cell can hold one byte of data. Once a cell's value goes above 255, it wraps around to 0. If a cell's value goes below 0, it wraps to 255.
If the result of an operation isn't an int, round the result down to the nearest one.
Your interpreter should ignore any non-command characters in the code.
If you come to a W in the code and the current cell is equal to 0, jump to the corresponding E.
If you come to an E in the code and the current cell is not 0, jump back to the corresponding W.
Here are the Poohbear commands:

Command	Definition
+	Add 1 to the current cell
-	Subtract 1 from the current cell
>	Move the cell pointer 1 space to the right
<	Move the cell pointer 1 space to the left
c	"Copy" the current cell
p	Paste the "copied" cell into the current cell
W	While loop - While the current cell is not equal to 0
E	Closing character for loops
P	Output the current cell's value as ascii
N	Output the current cell's value as an integer
T	Multiply the current cell by 2
Q	Square the current cell
U	Square root the current cell's value
L	Add 2 to the current cell
I	Subtract 2 from the current cell
V	Divide the current cell by 2
A	Add the copied value to the current cell's value
B	Subtract the copied value from the current cell's value
Y	Multiply the current cell's value by the copied value
D	Divide the current cell's value by the copied value.
 */

fun main() {
    assertEquals("Hello World!", Esoteric_Language_Poohbear_Interpreter("LQTcQAP>pQBBTAI-PA-PPL+P<BVPAL+T+P>PL+PBLPBP<DLLLT+P"))
    assertEquals("12345678910", Esoteric_Language_Poohbear_Interpreter("+c BANANA BANANA BANANA BANANA BANANA"))
    assertEquals("153", Esoteric_Language_Poohbear_Interpreter("LL+W c>p>p<- W are you trying to debug this? c>Y<- E>c>A<<<- E>>>N"))   // nested loops: sum of factorial(n) for n = 1 to 5
}


fun Esoteric_Language_Poohbear_Interpreter(e: String): String {
    
    val prg = e.filter{it in "+-><cpWEPNTQULIVABYD"}.toCharArray()
    val mem = mutableListOf<UByte>(0u)
    var prg_ptr = 0
    var mem_ptr = 0
    var reg: UByte = 0u
    val output = mutableListOf<String>()
    do {
        val cmd = prg[prg_ptr]
        when (cmd) {
            '+' -> mem[mem_ptr]++
            '-' -> mem[mem_ptr]--
            '>' -> mem_ptr = mem.moveRight(mem_ptr)
            '<' -> mem_ptr = mem.moveLeft(mem_ptr)
            'c' -> reg = mem[mem_ptr]
            'p' -> mem[mem_ptr] = reg
            'W' -> if(mem[mem_ptr].compareTo(0u)==0) prg_ptr = prg.getCorrE(prg_ptr)
            'E' -> if(mem[mem_ptr].compareTo(0u)!=0) prg_ptr = prg.getCorrW(prg_ptr)
            'P' -> output.add(mem[mem_ptr].toInt().toChar().toString())
            'N' -> output.add(mem[mem_ptr].toInt().toString())
            'T' -> mem[mem_ptr] = (mem[mem_ptr] * 2u).toUByte()
            'Q' -> mem[mem_ptr] = (mem[mem_ptr] * mem[mem_ptr]).toUByte()
            'U' -> mem[mem_ptr] = Math.sqrt(mem[mem_ptr].toInt().toDouble()).toInt().toUByte()
            'L' -> mem[mem_ptr] = (mem[mem_ptr] + 2u).toUByte()
            'I' -> mem[mem_ptr] = (mem[mem_ptr] - 2u).toUByte()
            'V' -> mem[mem_ptr] = (mem[mem_ptr] / 2u).toUByte()
            'A' -> mem[mem_ptr] = (mem[mem_ptr] + reg).toUByte()
            'B' -> mem[mem_ptr] = (mem[mem_ptr] - reg).toUByte()
            'Y' -> mem[mem_ptr] = (mem[mem_ptr] * reg).toUByte()
            'D' -> mem[mem_ptr] = (mem[mem_ptr] / reg).toUByte()
        }
        prg_ptr++
    } while(prg_ptr<prg.size)
    
    return output.joinToString("")
}

fun MutableList<UByte>.moveRight(curInd: Int): Int {
    val newInd = curInd + 1
    if(newInd>=size) add(0u)
    return newInd
}

fun MutableList<UByte>.moveLeft(curInd: Int): Int {
    var newInd = curInd - 1
    if(newInd<0) { add(0,0u); newInd = 0}
    return newInd
}

fun CharArray.getCorrW(currPtr: Int): Int {
    var moreEs = 0; var retInd = 0
    for(ind in currPtr-1 downTo 0) {
        when(this[ind]) {
            'E' -> moreEs++
            'W' -> if(moreEs>0) moreEs-- else { retInd = ind ; break }
        }
    }
    return retInd
}

fun CharArray.getCorrE(currPtr: Int): Int {
    var moreWs = 0; var retInd = 0
    for(ind in currPtr+1..lastIndex) {
        when(this[ind]) {
            'W' -> moreWs++
            'E' -> if(moreWs>0) moreWs-- else { retInd = ind; break }
        }
    }
    return retInd
}