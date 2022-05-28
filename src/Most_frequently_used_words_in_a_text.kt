
/*
Write a function that, given a string of text (possibly with punctuation and line-breaks), returns an array of the top-3 most occurring words, in descending order of the number of occurrences.

Assumptions:
A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in ASCII.
Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all valid)
Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as whitespace.
Matches should be case-insensitive, and the words in the result should be lowercased.
Ties may be broken arbitrarily.
If a text contains fewer than three unique words, then either the top-2 or top-1 words should be returned, or an empty array if a text contains no words.
 */

fun main() {
    assertEquals(listOf("a", "of", "on"), Most_frequently_used_words_in_a_text(sequenceOf(
        "In a village of La Mancha, the name of which I have no desire to call to",
        "mind, there lived not long since one of those gentlemen that keep a lance",
        "in the lance-rack, an old buckler, a lean hack, and a greyhound for",
        "coursing. An olla of rather more beef than mutton, a salad on most",
        "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
        "on Sundays, made away with three-quarters of his income."
    ).joinToString("\n")))
}


fun Most_frequently_used_words_in_a_text(s: String): List<String> {
    
    var text = s.map {
        if (it.isLetter() || it == '\'') it else ' '
    }.joinToString("").lowercase()
    
    var map = mutableMapOf<String,Int>()
    text.split(' ').forEach{
        val word = it.trim()
        if (word.filter{it != '\''}.length > 0) {
            map.putIfAbsent(word,0)
            map[word] = map[word]!! + 1
        }
    }
    
    val ret = map.toList().sortedByDescending{(k,v)->v}
        .take(3).toMap().keys.toList()
    
    
    return ret
}