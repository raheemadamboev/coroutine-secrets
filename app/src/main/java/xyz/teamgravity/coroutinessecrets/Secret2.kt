package xyz.teamgravity.coroutinessecrets

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Secret - 2.
 *
 * When coroutine is cancelled, finally block is entered. However, suspend functions inside finally block is skipped since coroutine state will be
 * in cancelled state.
 *
 * Avoid calling suspend functions in finally block.
 *
 * If you need to call suspend functions in finally block, use NonCancellable.
 *
 * ```
 * lifecycleScope.launch {
 *    val job = launch {
 *       createTempFile(applicationContext)
 *    }
 *    delay(500L)
 *    job.cancel()
 * }
 * ```
 */
suspend fun createTempFile(context: Context) {
    val file = File(context.filesDir, "my-file.txt")

    try {
        writeLotsOfLines(file)
    } finally {
        println("Finally block entered!")
        withContext(NonCancellable) {
            deleteFile(file)
        }
    }
}

private suspend fun writeLotsOfLines(file: File) {
    withContext(Dispatchers.IO) {
        repeat(100_000) {
            file.appendText("Raheem is the best!")
            ensureActive()
        }
    }
}

private suspend fun deleteFile(file: File) {
    withContext(Dispatchers.IO) {
        file.delete()
        println("File deleted!")
    }
}