import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    println("Main program starts : " + Thread.currentThread().name)

    GlobalScope.launch { // Thread: T1
        println("Fake work starts : " + Thread.currentThread().name) // Thread: T1
        delay(1000) // Coroutine is suspended, but thread T1 is free (not blocked)
        println("Fake work ends : " + Thread.currentThread().name) // Can be any thread, T1 or any other thread.
    }

    runBlocking { // Creates a coroutine that blocks the current main thread (parent thread)
        delay(1000) // Waiting for the coroutine to finish. (Practically, not a right way to call)
                            // suspending function can be called only from a coroutine or other suspending function.
    }

    println("Main program ends : " + Thread.currentThread().name)
}


// The above can be refactored in a different way, refer to 'RunBlocking.kt' code.