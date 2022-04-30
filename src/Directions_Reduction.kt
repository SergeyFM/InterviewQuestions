/*
Once upon a time, on a way through the old wild mountainous west,…
… a man was given directions to go from one point to another. The directions were "NORTH", "SOUTH", "WEST", "EAST". Clearly "NORTH" and "SOUTH" are opposite, "WEST" and "EAST" too.

Going to one direction and coming back the opposite direction right away is a needless effort. Since this is the wild west, with dreadfull weather and not much water, it's important to save yourself some energy, otherwise you might die of thirst!

Write a function which will take an array of strings and returns an array of strings with the needless directions removed (W<->E or S<->N side by side).
 */

fun main() {
    var a = arrayOf("NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST")
    assertEquals(arrayOf("WEST"),Directions_Reduction(a))
    a = arrayOf("NORTH", "WEST", "SOUTH", "EAST")
    assertEquals(arrayOf("NORTH", "WEST", "SOUTH", "EAST"),Directions_Reduction(a))
    a = arrayOf("NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "NORTH")
    assertEquals(arrayOf("NORTH"),Directions_Reduction(a))
    a = arrayOf("EAST", "EAST", "WEST", "NORTH", "WEST", "EAST", "EAST", "SOUTH", "NORTH", "WEST")
    assertEquals(arrayOf("EAST", "NORTH"),Directions_Reduction(a))
    a = arrayOf("NORTH", "EAST", "NORTH", "EAST", "WEST", "WEST", "EAST", "EAST", "WEST", "SOUTH")
    assertEquals(arrayOf("NORTH", "EAST"),Directions_Reduction(a))
    a = arrayOf("")
    assertEquals(arrayOf(""),Directions_Reduction(a))

}

fun Directions_Reduction(arr: Array<String>) = arr.map{it.uppercase()}.toMutableList().apply {
    var ptr = 0
    var found = false
    do {
        if(size<2) break
        if(ptr==0) found = false
        val two = listOf(this[ptr],this[ptr+1]).sorted().joinToString(" ")
        if(two in "NORTH SOUTH  EAST WEST") {
            (0..1).forEach{removeAt(ptr)}
            found = true
        }
        ptr++; if(ptr>size-2) ptr = 0
    } while( !(ptr==0 && found==false) )
}.toTypedArray()