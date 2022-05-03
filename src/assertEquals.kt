import java.util.*

// Quick test function

fun assertEquals(correct: Any, attempt: Any) {
    val correct2 = when(correct) {
        is Array<*> -> Arrays.toString(correct)
        is IntArray -> Arrays.toString(correct)
        is LongArray -> Arrays.toString(correct)
        else -> correct.toString()
    }
    val attempt2 = when(attempt) {
        is Array<*> -> Arrays.toString(attempt)
        is IntArray -> Arrays.toString(attempt)
        is LongArray -> Arrays.toString(attempt)
        else -> attempt.toString()
    }
    
    println(
        if(correct2==attempt2) "$attempt2 OK" else "$attempt2 is INCORRECT! (should be $correct2)"
    )
}