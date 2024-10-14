import kotlin.concurrent.thread

fun main() {
    println("Main program starts: ${Thread.currentThread().name}")

    thread { //creates a background thread (worker thread)
        println("Upload task started: ${Thread.currentThread().name}")

        Thread.sleep(2000) // simulating file upload task

        println("Upload task finished: ${Thread.currentThread().name}")
    }

    println("Main program ends: ${Thread.currentThread().name}")
}

/*
* Here, the background thread takes care of the upload process
* while main thread resumes (Worker thread did not block the main thread)
*
* Also, in case of threads the application thread aka main thread will eventually wait for all the worker threads to complete their tasks before ending the application
*
* Unlike threads, application thread (main thread) will not wait for the completion of tasks by coroutines.
*
*
 o/p:
        Main program starts: main
        Main program ends: main
        Upload task started: Thread-0
        Upload task finished: Thread-0
* */