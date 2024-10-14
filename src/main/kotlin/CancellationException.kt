import kotlinx.coroutines.*
import kotlin.coroutines.cancellation.CancellationException

fun main() = runBlocking{

    println("Main program starts : " + Thread.currentThread().name) // Thread: main

    val job: Job = launch {
        println("Coroutine starts : " + Thread.currentThread().name) // Thread: main

        try {
            for (i in 1..1000) {
                println("Processing item $i")
                delay(10) // Ypu can use delay() or any other suspending function that actively checks for cancellation
            }
        } catch (e: CancellationException) {
            println("\nException caught safely - " + e.message) //Making use of my custom exception message
        } finally {
            withContext(NonCancellable) {
                println("\nWithContext finally block starts : " + Thread.currentThread().name) // Thread: main

                delay(100)
                println("Closed resources in the finally block")

                println("WithContext finally block ends : " + Thread.currentThread().name) // Thread: main
            }
            /*
            *
            * Generally, we do not use suspending functions inside finally block
            * because the coroutine running the code is already cancelled
            *
            * As you can see the o/p println statement inside the finally is not executed since an exception is thrown by the suspending function
            *
            * In some exceptional scenarios, if you want to make use of suspending functions inside the finally block, use
            * withContext(NonCancellable) function
            *
            * withContext { } is a coroutine builder.
            *
            * So, withContext creates a new coroutine in another context and this will execute this suspending function
            *
            * Inside the finally, withContext(NonCancellable) is used.
            * This ensures that the code inside the withContext block will not be canceled even though the coroutine was canceled.
            *
            * */
        }

        println("\nCoroutine ends : " + Thread.currentThread().name) // Thread: main
    }

    delay(100) // Lets print a few values before we cancel

    //job.cancelAndJoin()

    job.cancel(CancellationException("My custom cancellation message"))
    job.join()

    /*
    *
    * You can print your own custom Cancellation message using
    *
    * job.cancel("CancellationException("My custom exception message"))
    *
    * Note that we have to split job.cancelAndJoin() to job.cancel() and job.join() for this kind of messaging
    *
    * */
    println("\nMain program ends : " + Thread.currentThread().name) // Thread: main
}