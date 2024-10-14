@file:OptIn(DelicateCoroutinesApi::class)

import kotlinx.coroutines.*

fun main() {
    println("Main program starts: ${Thread.currentThread().name}") // Main thread

    GlobalScope.launch{ // Creates a background coroutine that runs on background thread
        println("Uploading starts: ${Thread.currentThread().name}")

        Thread.sleep(2000) //uploading task

        println("Uploading ends: ${Thread.currentThread().name}")
    }

    Thread.sleep(3000) // Blocks the current main thread and wait for the upload task (performed by coroutine) to finish (not a practical solution)
                            // If I do not use this Thread.sleep(3000), o/p would be different

    println("Main program starts: ${Thread.currentThread().name}") // Main thread
}


/*
*
* GlobalScope.launch creates a background coroutine that runs on the background thread
*
* Unlike threads, application thread (main thread) will not wait for the completion of tasks by coroutines. Hence, we had to put  Thread.sleep(3000)
*
* */

/*
*
*
o/p:

Main program starts: main
Uploading starts: DefaultDispatcher-worker-1
Uploading ends: DefaultDispatcher-worker-1
Main program starts: main
*
*
*
* */