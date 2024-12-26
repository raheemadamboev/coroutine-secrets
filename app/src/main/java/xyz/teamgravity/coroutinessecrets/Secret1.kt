package xyz.teamgravity.coroutinessecrets

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.io.Closeable

/**
 * Secret - 1.
 *
 * If scope should not be cancelled when one of child coroutine is cancelled or exception thrown,
 * SupervisorJob() should be passed for coroutineContext when creating custom CoroutineScope.
 *
 * If SupervisorJob() wasn't passed, only childB would be completed and childA would be cancelled:
 *
 * ```
 * // childA
 * scope.launch {
 *   delay(1_000L)
 *   println("childA is completed.")
 * }
 *
 * // childB
 * scope.launch {
 *   delay(500L)
 *   println("childB is completed.")
 *   error("Error is thrown!")
 * }
 * ```
 */
class Manager : Closeable {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob() + CoroutineExceptionHandler { _, _ -> })

    override fun close() {
        scope.cancel()
    }
}