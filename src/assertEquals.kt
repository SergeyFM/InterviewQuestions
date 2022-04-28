// Quick test function

fun assertEquals(correct: Any, attempt: Any) {
    println(
        if(correct==attempt) "$attempt OK" else "$attempt is INCORRECT! (should be $correct)"
    )
}