/*
A sandpile is a grid of piles of sand ranging from 0 to some max integer value. For simplicity's sake, we'll look at a 3x3 grid containing 0, 1, 2, or 3 grains of sand.

Sandpiles are a form of number, and as numbers, they support a single operation: addition.
To add two sandpiles, simply add the number of grains of sand within each cell with the matching cell in the second value:

1 3 0   2 0 2   (1+2) (3+0) (0+2)   3 3 2
1 2 1 + 2 1 0 = (1+2) (2+1) (1+0) = 3 3 1
3 2 2   0 0 1   (3+0) (2+0) (2+1)   3 2 3

You probably already have wondered, what happens if the number of grains goes above our max value of 3? The answer is, that pile topples over. If the pile is in the middle, it dumps one grain of sand to each neighbor, keeping whatever is left over. If it's on the edge, it loses one grain to each direct neighbor and also loses one grain for any edges that are on the side, which just disappear. This means that, no matter what, the over-sized cell loses 4 grains of sand, while any neighboring cells gain 1 grain of sand.

The Challenge
We want to create a class, Sandpile, that simulates the 3x3, max 3 sandpile. This class should have the following properties:

The constructor optionally takes a string, which is 3 groups of 3 integers (0-9), separated by a newline. If any of the values of the integers are over 3, then immediately topple each pile until the Sandpile is at rest. If no argument is provided, initialize the piles with all zeros.
There should be a toString method, which prints the sandpile the same way as the constructor. This will be used to validate your results.
There should be an add method, which takes another Sandpile as an argument. This method returns a new Sandpile, with the sum of the previous Sandpiles.
Sandpiles are immutable after creation
To let you focus on the fun part of this challenge, you don't have to worry about validation:
The input to the contructor will either be nothing or a string that matches /[0-9]{3}\n[0-9]{3}\n[0-9]{3}/
The add function will only ever receive another Sandpile instance.

 */




fun main() {
    println("should create and render an empty sandpile")
    var s = Playing_with_Sandpiles().toString()
    assertEquals("000\n000\n000",s)
    
    println("should create a custom sandpile")
    s = Playing_with_Sandpiles("130\n121\n322").toString()
    assertEquals("130\n121\n322",s)
    
    println("should handle adding two empty sandpiles")
    var sp1 = Playing_with_Sandpiles("000\n000\n000")
    var sp2 = Playing_with_Sandpiles()
    var sp3 = sp1.add(sp2)
    assertEquals("000\n000\n000",sp3.toString())
    
    println("should handle adding an empty sandpile to a non-empty one")
    sp1 = Playing_with_Sandpiles("130\n121\n322")
    sp2 = Playing_with_Sandpiles()
    sp3 = sp1.add(sp2)
    assertEquals("130\n121\n322",sp3.toString())
    
    println("should not mutate the original sandpiles")
    sp1 = Playing_with_Sandpiles("130\n121\n322")
    sp2 = Playing_with_Sandpiles()
    sp3 = sp1.add(sp2)
    assertEquals("130\n121\n322",sp1.toString())
    assertEquals("000\n000\n000",sp2.toString())
    assertTrue(sp1 != sp3)
    assertTrue(sp2 != sp3)
    
    println("should handle adding two non-empty sandpiles")
    sp1 = Playing_with_Sandpiles("130\n121\n322")
    sp2 = Playing_with_Sandpiles("202\n210\n001")
    sp3 = sp1.add(sp2)
    assertEquals(sp3.toString(),"332\n331\n323")
    
    println("should handle toppling a custom sandpile")
    var sp = Playing_with_Sandpiles("430\n121\n322")
    assertEquals("101\n231\n322",sp.toString())
    
    println("should handle adding and toppling two non-empty sandpiles")
    sp1 = Playing_with_Sandpiles("130\n121\n322")
    sp2 = Playing_with_Sandpiles("302\n230\n001")
    sp3 = sp1.add(sp2)
    assertEquals("230\n311\n121",sp3.toString())
    
    println("should be transitive")
    sp3 = sp2.add(sp1)
    assertEquals("230\n311\n121",sp3.toString())
    
    println("should handle toppling bigger inputs")
    sp = Playing_with_Sandpiles("987\n654\n321")
    assertEquals("021\n233\n330",sp.toString())
    
    println("should handle toppling the biggest input")
    sp = Playing_with_Sandpiles("999\n999\n999")
    assertEquals("131\n313\n131",sp.toString())
    
    println("should handle a surprising outcome")
    sp1 = Playing_with_Sandpiles("222\n222\n222")
    sp2 = Playing_with_Sandpiles("212\n101\n212")
    sp3 = sp1.add(sp2)
    assertEquals("222\n222\n222",sp3.toString())
}



class Playing_with_Sandpiles(piles:String = "000\n000\n000") {
    
    val grid: MutableList<Int>
    val row_len: Int
    val max_val = 3
    
    init {
        row_len = piles.split("\n").first().length
        grid = toList(piles)
        topple()
    }
    
    fun add(sandpile:Playing_with_Sandpiles): Playing_with_Sandpiles {
        val grid_to_add = sandpile.grid
        val new_grid = grid.mapIndexed {ind,item->
            item+(grid_to_add.elementAtOrElse(ind){0})
        }.chunked(row_len).map{it.joinToString("")}.joinToString("\n")
        return Playing_with_Sandpiles(new_grid)
    }
    
    override fun toString() = grid.chunked(row_len).map{it.joinToString("")}.joinToString("\n")
    
    private fun toList(p: String): MutableList<Int> = p.split("\n").map{row->
        row.map{c-> "$c".toIntOrNull()?:0}
    }.flatten().toMutableList()
    
    private fun topple() {
        do {var any_changes = false
            for(ind in 0..grid.lastIndex) {
                if(grid[ind]>max_val) {
                    any_changes = true
                    grid[ind] -= max_val+1
                    if(ind-1>=0 && ind%row_len>0) grid[ind-1]++ //left
                    if(ind+1<=grid.lastIndex && ind%row_len<row_len-1) grid[ind+1]++ //right
                    if(ind-row_len>=0) grid[ind-row_len]++ //up
                    if(ind+row_len<=grid.lastIndex) grid[ind+row_len]++ //down
                }
            }
        } while(any_changes)
    }
    
}