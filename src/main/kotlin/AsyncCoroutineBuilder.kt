import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{

    println("Main program starts : " + Thread.currentThread().name) // Thread: main
    // Since this print statement is in the scope of runBlocking method,
    // which is running on main thread,

    val deferredJob: Deferred<Int> = async { // Creates a new coroutine in the local scope, Thread: main
        /*
        *
        * Deferred class is a subclass of Job i/f
        *
        * */
        /*
        * async { } coroutine builder is actually present within the scope of the runBlocking { } coroutine builder
        *
        * Hence, async { } runs on the main thread which is the scope of runBlocking { }
        *
        * If we make use of the GlobalScope.async { } coroutine builder, it creates a new coroutine in the global scope (Thread would be different, not main)
        *
        * And also notice the difference in outputs.
        *
        * You see main thread when async { } coroutine builder is used
        *
        * You see DefaultDispatcher-worker-1 thread when GlobalScope.async { } coroutine builder is used
        *
        * */
        println("Fake work starts : " + Thread.currentThread().name) // Thread: main
        delay(1000) // Coroutine is suspended, but thread main is free (not blocked)
        println("Fake work ends : " + Thread.currentThread().name) // Can be any thread, main or any other thread.

        100
    }

    /*
    *  Similar to delay(t), await() and join() are suspending functions, meaning should be used in coroutine scope. (In our case runBlocking coroutine builder)
    *
    * If you just want to make use of the returned value and do not care about the execution part,
    * you can use - deferredJob.await()
    *
    *
    * Conversely, if you don't care about the returned value just concerned about the execution part,
    * you can use - deferredJob.join()
    *
    *
    *
      await(): Always waits for the asynchronous coroutine to complete and returns the result of the computation.
               The await() function suspends the calling coroutine until the deferred computation is completed.
               This is necessary because the result depends on the execution of asynchronous logic.
               If the result is not needed and you only want to wait for the completion (without the result), you can use join().
               But if you need the computed result, await() is mandatory, and you cannot avoid executing the logic inside the coroutine.

      join(): Only waits for completion without returning any result.

      If the result is computed dynamically (not hardcoded), the behavior will be the same.
      You cannot bypass execution of the coroutine if you want to get the result because await() ensures that the coroutine has fully executed before it returns the value.
    * */

    val result: Int = deferredJob.await() // 100

    /*
    * delay(1000) // Thread: main, Waiting for the coroutine to finish. (Practically, not a right way to call)
    *
    * Since delay() is not the correct way to wait for the coroutine to finish its task, we make use of methods pertaining to Job
    *
    * */

   // deferredJob.join()

    /*
    *
    * This join() will wait for the coroutine to finish its task to be executes and only then the next statement is executed.
    *
    * Similarly, you can cancel the job using job.cancel()
    *
    * */

    println("Main program ends : " + Thread.currentThread().name) // Thread: main
}