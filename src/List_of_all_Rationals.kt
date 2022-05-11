/*
Here's a way to construct a list containing every positive rational number:

Build a binary tree where each node is a rational and the root is 1/1, with the following rules for creating the nodes below:

The value of the left-hand node below a/b is a/a+b
The value of the right-hand node below a/b is a+b/b
So the tree will look like this:

                       1/1
                  /           \
            1/2                  2/1
           /    \              /     \
       1/3        3/2        2/3       3/1
      /   \      /   \      /   \     /   \
   1/4    4/3  3/5   5/2  2/5   5/3  3/4   4/1

 ...
Now traverse the tree, breadth first, to get a list of rationals.

[ 1/1, 1/2, 2/1, 1/3, 3/2, 2/3, 3/1, 1/4, 4/3, 3/5, 5/2, .. ]
Every positive rational will occur, in its reduced form, exactly once in the list, at a finite index.
 */

fun main() {
    val a = List_of_all_Rationals().asSequence().take(100001).toList()
    assertEquals(Pair(19, 12), a[100])
    assertEquals(Pair(39, 28), a[1000])
    assertEquals(Pair(205, 162), a[10000])
    assertEquals(Pair(713, 586), a[100000])
}


fun List_of_all_Rationals(): Iterator<Pair<Int, Int>> = iterator {
    
    var layer = mutableListOf(Pair(1,1))
    
    while(true) {
        layer.forEach { n -> yield(n)}
        val new_layer = mutableListOf<Pair<Int,Int>>()
        layer.forEach { n ->
            val a = n.first
            val b = n.second
            new_layer.add(Pair(a,a+b))
            new_layer.add(Pair(a+b,b))
        }
        layer = new_layer
        
    }
    
    
}