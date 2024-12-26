package xyz.teamgravity.coroutinessecrets

import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration.Companion.seconds

/**
 * Secret - 3.
 *
 * When catching general exceptions inside coroutine, rethrow the CancellationException or preferably use coroutineContext.ensureActive().
 *
 * ```
 * lifecycleScope.launch {
 *    val job = launch {
 *       fetchNetwork()
 *    }
 *    delay(5_000L)
 *    job.cancel()
 * }
 * ```
 */
suspend fun fetchNetwork() {
    while (true) {
        try {
            println("Polling network resource...")

            delay(0.5.seconds)

            println("Received post!")

            delay(10.seconds)
        } catch (e: Exception) {
            println("Oops, something went wrong, make sure you're connected to the internet.")
//            if (e is CancellationException) throw e
            coroutineContext.ensureActive()
        }
    }
}