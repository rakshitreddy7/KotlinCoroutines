import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts : " + Thread.currentThread().name) // Thread: main
    println("Parent context: ${coroutineContext[Job]}")

    withTimeout(100) { // Coroutine builder
        println("\nTimeout block starts : " + Thread.currentThread().name) // Thread: main
        println("Timeout block context: ${coroutineContext[Job]}\n")

        try {
            for (i in 1..1000) {
                println("Processing - $i")
                delay(10)
            }
        } catch (ex: TimeoutCancellationException) {
            println("\nException caught safely - " + ex.message) //Making use of my custom exception message
        } finally {
            println("\nFinally block starts : " + Thread.currentThread().name) // Thread: main
            println("Closed resources in the finally block")
            println("Finally block ends : " + Thread.currentThread().name) // Thread: main
        }

        println("\nTimeout block starts : " + Thread.currentThread().name) // Thread: main
        println("Timeout block context: ${coroutineContext[Job]}\n")
    }

    /*
    *
    * When the set time in withTimeout exceeds TimeoutCancellationException is thrown
    *
    * TimeoutCancellationException is a subclass of CancellationException
    *
    * withTimeoutOrNull works same as withTimeout, but withTimeoutOrNull
    * does not throw any exception and when the coroutine returns some value
    *
    * Hence, we can remove try catch finally blocks when we make use of withTimeoutOrNull
    *
    * withTimeoutOrNull will return some value from the coroutine int he form of Lambda result
    *
    * Checkout the example for withTimeoutOrNull
    *
    * */

    println("Main program ends : " + Thread.currentThread().name) // Thread: main
    println("Parent context: ${coroutineContext[Job]}")
}