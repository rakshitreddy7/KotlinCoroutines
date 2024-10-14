import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts : " + Thread.currentThread().name) // Thread: main
    println("Parent context: ${coroutineContext[Job]}")

    val result: String? = withTimeoutOrNull(1000) { // Coroutine builder
        println("\nTimeout block starts : " + Thread.currentThread().name) // Thread: main
        println("Timeout block context: ${coroutineContext[Job]}\n")

        for (i in 1..1000) {
            println("Processing - $i")
            delay(100)
        }

        println("\nTimeout block ends : " + Thread.currentThread().name) // Thread: main
        println("Timeout block context: ${coroutineContext[Job]}")

        "Hello World"
    }

    /*
    * If the task is not completed in the set time, NULL is returned instead of the actual value.
    *
    * Also, note that the result's datatype should be Nullable
    *
    * */

    println("\nResult : $result")

    println("\nMain program ends : " + Thread.currentThread().name) // Thread: main
    println("Parent context: ${coroutineContext[Job]}")
}