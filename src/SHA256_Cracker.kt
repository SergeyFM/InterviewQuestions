/*
The aim is to show the vulnerabilities of hashing functions for short messages.

When provided with a SHA-256 hash, return the value that was hashed. You are also given the characters that make the expected value, but in alphabetical order.

The returned value is less than 10 characters long.

Example:
Example arguments: '5694d08a2e53ffcae0c3103e5ad6f6076abd960eb1f8a56577040bc1028f702b', 'cdeo'
Correct output: 'code'
 */

fun main() {
    assertEquals("GoOutside", SHA256_Cracker("b8c49d81cb795985c007d78379e98613a4dfc824381be472238dbd2f974e37ae", "deGioOstu"))
    assertEquals(null, SHA256_Cracker("f58262c8005bb64b8f99ec6083faf050c502d099d9929ae37ffed2fe1bb954fb", "abc"))
}

fun SHA256_Cracker(hash: String, chars: String): String? {
    
    print ("$hash ?= [$chars] ")
    
    val word = chars
    val len = word.length
    if (len > 10 || len < 1) return null
    val md = java.security.MessageDigest.getInstance("SHA-256")
    
    val factorials = mutableListOf(1)
    (1..len).forEach { factorials.add(factorials[it-1] * it) }
    
    (0..factorials[len]-1).forEach { i ->
        var onePermutation = ""
        var temp = word
        var positionCode = i
        (len downTo 1).forEach { position ->
            val selected = positionCode / factorials[position-1]
            onePermutation += temp.get(selected)
            positionCode = positionCode % factorials[position-1]
            temp = temp.substring(0,selected) + temp.substring(selected+1)
        }
        val hash_candidate = md
            .digest(onePermutation.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
        if (hash_candidate == hash) {
            println ("FOUND $onePermutation")
            return onePermutation
        }
        
    }
    
    println ("NOT FOUND")
    return null
}