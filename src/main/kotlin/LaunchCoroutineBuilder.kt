import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{

    println("Main program starts : " + Thread.currentThread().name) // Thread: main
    // Since this print statement is in the scope of runBlocking method,
    // which is running on main thread,

    val job: Job = launch { // Creates a new coroutine in the local scope, Thread: main
        /*
        * launch { } coroutine builder is actually present within the scope of the runBlocking { } coroutine builder
        *
        * Hence, launch { } runs on the main thread which is the scope of runBlocking { }
        *
        * If we make use of the GlobalScope.launch { } coroutine builder, it creates a new coroutine in the global scope (Thread would be different, not main)
        *
        * And also notice the difference in outputs.
        *
        * You see main thread when launch { } coroutine builder is used
        *
        * You see DefaultDispatcher-worker-1 thread when GlobalScope.launch { } coroutine builder is used
        *
        * */
        println("Fake work starts : " + Thread.currentThread().name) // Thread: main
        delay(3000) // Coroutine is suspended, but thread main is free (not blocked)
        println("Fake work ends : " + Thread.currentThread().name) // Can be any thread, main or any other thread.
    }

    /*
    * delay(1000) // Thread: main, Waiting for the coroutine to finish. (Practically, not a right way to call)
    *
    * Since delay() is not the correct way to wait for the coroutine to finish its task, we make use of methods pertaining to Job
    *
    * */

    job.join()

    /*
    *
    * This join() will wait for the coroutine to finish its task to be executed and only then the next statement is executed.
    *
    * Similarly, you can cancel the job using job.cancel()
    *
    * */

    println("Main program ends : " + Thread.currentThread().name) // Thread: main
}