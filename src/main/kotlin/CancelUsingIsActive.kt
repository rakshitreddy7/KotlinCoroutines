import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts : " + Thread.currentThread().name) // Thread: main

    val job: Job = launch(Dispatchers.Default) {
        println("Coroutine starts : " + Thread.currentThread().name) // Thread: DefaultDispatcher-worker-1

        for (i in 1..1000) {
            println("Processing item $i")

            if(!isActive) { // As soon as the job is cancelled using job.cancelAndJoin(), isActive is set to 'false' (meaning, coroutine is cancelled)
                return@launch
                /*
                *   Use break to exit a loop when a condition is met.
                *
                *   Use return@launch to exit the coroutine block early, skipping any remaining code in that coroutine.
                *
                * */
            }
        }

        println("Coroutine ends : " + Thread.currentThread().name) // Thread: DefaultDispatcher-worker-1
    }

    delay(5) // Let's print a few values before we cancel
    job.cancelAndJoin()

    println("Main program ends : " + Thread.currentThread().name) // Thread: main
}