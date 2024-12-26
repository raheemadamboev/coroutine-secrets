package xyz.teamgravity.coroutinessecrets

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import java.io.ByteArrayOutputStream

/**
 * Secret - 5.
 *
 * When calling blocking function in suspend functions, actively check for cancellation.
 */
class ImageReader(
    private val context: Context
) {

    suspend fun readImageFromUri(contentUri: Uri): ByteArray {
        return withContext(Dispatchers.IO) {
            return@withContext context.contentResolver.openInputStream(contentUri)?.use { input ->
                return@use ByteArrayOutputStream().use output@{ output ->
                    var byte = input.read()

                    while (byte != -1) {
                        output.write(byte)
                        byte = input.read()
                        yield()
                    }

                    return@output output.toByteArray()
                }
            } ?: byteArrayOf()
        }
    }
}