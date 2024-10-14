import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    println("Main program starts : " + Thread.currentThread().name)

    GlobalScope.launch { // Thread: T1
        println("Fake work starts : " + Thread.currentThread().name)
        Thread.sleep(1000)
        println("Fake work ends : " + Thread.currentThread().name)
    }

    println("Main program ends : " + Thread.currentThread().name)
}


// As you can notice, parent thread (main) does not wait for the coroutine's task execution