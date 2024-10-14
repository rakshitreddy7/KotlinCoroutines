import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking { // Executes in main thread
                          // Creates a coroutine that blocks the current (main) thread (Since this coroutine is running on the main thread)

        println("Main program starts : " + Thread.currentThread().name) // Thread: main
        // Since this print statement is in the scope of runBlocking method,
        // which is running on main thread,

        GlobalScope.launch { // Creates a new coroutine, but on a background thread, Thread: T1
            println("Fake work starts : " + Thread.currentThread().name) // Thread: T1
            customDelay(1000) // Coroutine is suspended, but thread T1 is free (not blocked)
            println("Fake work ends : " + Thread.currentThread().name) // Can be any thread, T1 or any other thread.
        }

        /*
        * Note that, GlobalScope.launch creates a new coroutine on a different background thread (T1) and
        * all the code inside GlobalScope.launch is executes parallely in concurrent manner in the background
        * */

    customDelay(1000) // Thread: main
        // Waiting for the coroutine to finish. (Practically, not a right way to call)

        println("Main program ends : " + Thread.currentThread().name) // Thread: main
}

suspend fun customDelay(time: Long) {
    /*
    * My custom code
    * */

    delay(time)
}