/*
Your granny, who lives in town X0, has friends. These friends are given in an array, for example: array of friends is ["A1", "A2", "A3", "A4", "A5"].

The order of friends in this array must not be changed since this order gives the order in which they will be visited.

Friends inhabit towns and you get an array with friends and their towns (or an associative array), for example: [["A1", "X1"], ["A2", "X2"], ["A3", "X3"], ["A4", "X4"]] which means A1 is in town X1, A2 in town X2... It can happen that we do not know the town of one of the friends hence it will not be visited.

Your granny wants to visit her friends and to know approximately how many miles she will have to travel. You will make the circuit that permits her to visit her friends. For example here the circuit will be:X0, X1, X2, X3, X4, X0 and you will compute approximately the total distance X0X1 + X1X2 + .. + X4X0.

For the distances you are given an array or a dictionary that gives each distance X0X1, X0X2 and so on. For example (it depends on the language):

[ ["X1", 100.0], ["X2", 200.0], ["X3", 250.0], ["X4", 300.0] ]
or
("X1" -> 100.0, "X2" -> 200.0, "X3" -> 250.0, "X4" -> 300.0)
which means that X1 is at 100.0 miles from X0, X2 at 200.0 miles from X0, etc... It's not real life, it's a story... : the towns X0, X1, .., X0 are placed in the following manner (see drawing below):

X0X1X2 is a right triangle with the right angle in X1, X0X2X3 is a right triangle with the right angle in X2, ... In a travel X0, X1, .., Xi-1, Xi, Xi+1.., X0 you will suppose - to make it easier - that there is a right angle in Xi (i > 0).

So if a town Xi is not visited you will consider that the triangle X0Xi-1Xi+1 is still a right triangle in Xi-1 and you can use the "Pythagorean_theorem".

Task
Can you help your granny and give her approximately the distance to travel?
 */

fun main() {
    val friends1 = arrayOf("A1", "A2", "A3", "A4", "A5")
    val fTowns1 = arrayOf(arrayOf("A1", "X1"), arrayOf("A2", "X2"), arrayOf("A3", "X3"), arrayOf("A4", "X4"))
    val distTable1: MutableMap<String, Double> = HashMap()
    distTable1["X1"] = 100.0
    distTable1["X2"] = 200.0
    distTable1["X3"] = 250.0
    distTable1["X4"] = 300.0
    assertEquals(889, Help_your_granny(friends1, fTowns1, distTable1))
    
    val friends2 = arrayOf("A1", "A2", "A3", "A4", "A5", "A6")
    val fTowns2 = arrayOf(arrayOf("A1", "X1"), arrayOf("A2", "X2"), arrayOf("A3", "X3"), arrayOf("A4", "X4"), arrayOf("A5", "X5"))
    val distTable2: MutableMap<String, Double> = HashMap()
    distTable2["X1"] = 100.0
    distTable2["X2"] = 200.0
    distTable2["X3"] = 250.0
    distTable2["X4"] = 300.0
    distTable2["X5"] = 320.0
    assertEquals(1020, Help_your_granny(friends2, fTowns2, distTable2))
    
    val friends3 = arrayOf("B1", "B2")
    val fTowns3 = arrayOf(arrayOf("B1", "Y1"), arrayOf("B2", "Y2"), arrayOf("B3", "Y3"), arrayOf("B4", "Y4"), arrayOf("B5", "Y5"))
    val distTable3: MutableMap<String, Double> = HashMap()
    distTable3["Y1"] = 50.0
    distTable3["Y2"] = 70.0
    distTable3["Y3"] = 90.0
    distTable3["Y4"] = 110.0
    distTable3["Y5"] = 150.0
    assertEquals(168, Help_your_granny(friends3, fTowns3, distTable3))
    
}

fun Help_your_granny(arrFriends: Array<String>, ftwns: Array<Array<String>>, h: Map<String, Double>): Int {
    
    val towns = ftwns.map{Pair(it[0],it[1])}.toMap()
    val visits = arrFriends.mapNotNull{towns.get(it)}
    val distances = visits.mapNotNull{h.get(it)}.toMutableList().apply{add(last())}
    val circuit = mutableListOf<Double>().apply{addAll(distances)}
    
    if(distances.size>1) (1..distances.size-2).forEach {ind->
        val a = distances[ind-1]
        val c = distances[ind]
        val b = Math.sqrt(c*c-a*a)
        circuit[ind] = b
    }
    return circuit.sum().toInt()
}