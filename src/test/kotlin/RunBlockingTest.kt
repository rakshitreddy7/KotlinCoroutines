import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RunBlockingTest {

    @Test
    fun myTest() = runBlocking {
        /*
        *
        * If I just make use of 'customDelay(1000)', compiler complains because
        *
        * Suspending functions can be called only from another 'suspending function' or from a 'coroutine'
        *
        * Here, customDelay(1000) is a suspending function, hence we need runBlocking { } coroutine builder
        * that will launch a coroutine and that coroutine blocks the current thread on which it is operating
        *
        * Note:
          ----
        * In Kotlin, runBlocking is often used in tests to execute suspending functions because it provides a simple way to
          block the main thread and run coroutines in a synchronous manner, which is necessary for unit testing.


          Blocking the Main Thread:
          ------------------------
            In a test, you often want to run suspending functions in a 'synchronous' and controlled manner.

            Since suspending functions need to be run within a coroutine scope,
            runBlocking helps by creating a coroutine scope that blocks the current thread until all coroutines inside it complete.

            This blocking behavior allows the test to wait for the coroutine to finish its work,
            ensuring that suspending functions can be tested as part of a normal test case.

          Simplifying Testing of Coroutines:
          ---------------------------------
            Using runBlocking, you can directly call suspending functions from within the test
            without needing additional setup like a custom coroutine dispatcher.

            It behaves similarly to regular function calls, so you don't need to change much of your code structure when writing unit tests for suspending functions.

        * */
        customDelay(1000)
        Assert.assertEquals(10, 5 + 5)
    }
}