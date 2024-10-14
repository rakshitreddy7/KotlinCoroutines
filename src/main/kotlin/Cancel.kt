import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Main program starts : " + Thread.currentThread().name) // Thread: main

    val job: Job = launch {
        println("Coroutine starts : " + Thread.currentThread().name) // Thread: main

        for (i in 1..100) {
            println("Processing item $i")

            yield() // This func will constantly check if this coroutine is cancelled or not
                                // If at a particular point cancelled, it will cancel the entire coroutine

             //delay(5)

            /*
            * If you don't want your coroutine to delay, yield() can be used.
            *
            * Hence can be faster (as you can see in the o/p, more print statements are printed when yield is used)
            *
            * */

            //Thread.sleep(50)

            /*
            *
            * job.cancel() does not work because this coroutine is NOT cooperative since Thread.sleep() is used and this will not actively check for cancellation
            * or yielding control.
            *
            *
            * Rather, if we make use of delay() or yield() [functions that actively check for cancellation or yielding control]
            *
            * Hence, job.cancel() will work if we make use of delay() or yield()
            *
            * */
        }

        println("Coroutine ends : " + Thread.currentThread().name) // Thread: main
    }

    delay(20) // Let's print a few values before we cancel

    job.cancelAndJoin()

   /* job.cancel()
      job.join()
   */

    // combined - job.cancelAndJoin()

    println("Main program ends : " + Thread.currentThread().name) // Thread: main
}