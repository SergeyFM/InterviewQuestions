/*
You are a lonely frog.
You live on an integer array.
The meaning of your life is to jump and jump..
Now, here comes your new task.
You are given an non negative integer array arr.
You will jump following the rules below:
The start position at arr[0], and the frog always jumping to the right.
The last(right most) position of arr is the exit.
In each turn, the frog can jump a distance between 0 and arr[i], where i is frog's current position. For example, if frog's current position is 1, and arr[1]=3, the frog can jump to position 2, 3 or 4. Of course, if arr[i] is 0, the frog can not jump to any position from the current position.
Your task is to complete function jumping. Return ture if the frog can reach the exit. Otherwise, return false.
 */

fun main() {
    assertEquals(true, Lonely_Frog_V(intArrayOf(2,3,1,1,4)))
    assertEquals(false, Lonely_Frog_V(intArrayOf(3,2,1,0,4)))
    assertEquals(true, Lonely_Frog_V(intArrayOf(1,2,0,3,0,0,0)))
    assertEquals(false, Lonely_Frog_V(intArrayOf(1,2,0,3,0,0,0,9)))
    assertEquals(false, Lonely_Frog_V(intArrayOf(0,1,2,3)))
    assertEquals(true, Lonely_Frog_V(intArrayOf(4,0,0,0,1)))
    assertEquals(true, Lonely_Frog_V(intArrayOf(9,3,2,1,0,3,2,1,0,1)))
}


fun Lonely_Frog_V(arr: IntArray) : Boolean  {
    if(arr.size<2) return true
    var ptr = arr.size-2
    var abyss = 0
    do {
        val place = arr[ptr]
        if(place==0 || place<=abyss)  ++abyss
        if(place>0 && place>abyss) abyss = 0
    } while(--ptr>=0)
    return abyss==0
}