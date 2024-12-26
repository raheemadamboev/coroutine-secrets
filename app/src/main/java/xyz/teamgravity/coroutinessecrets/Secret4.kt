package xyz.teamgravity.coroutinessecrets

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Secret - 4.
 *
 * Do not hardcode Dispatchers. Use dependency injection to provide them as you need to use special test dispatchers for properly
 * unit testing coroutines.
 */
class Writer(
    private val dispatcher: DispatcherProvider
) {

    suspend fun write() {
        withContext(dispatcher.io) {
            // do something
        }
    }
}

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}

object StandardDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
}

object TestDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher = TODO("Not yet implemented")
    override val io: CoroutineDispatcher = TODO("Not yet implemented")
    override val default: CoroutineDispatcher = TODO("Not yet implemented")
}