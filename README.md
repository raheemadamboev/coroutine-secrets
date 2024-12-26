# coroutines-secrets

A simple app that demonstrates 5 Kotlin Coroutines secrets.

1. `SupervisorJob()` in custom `CoroutineScope`.
2. `NonCancellable` when calling `suspend` functions in `finally` block.
3. `coroutineContext.ensureActive()` when catching general exception inside a coroutine.
4. Providing `Dispatchers` instead of harcoding them.
5. Calling `yield()` in blocking calls inside a coroutine.
