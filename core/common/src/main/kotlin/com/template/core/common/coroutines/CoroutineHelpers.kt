package com.template.core.common.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

suspend fun <T> AppDispatchers.withDefault(block: suspend CoroutineScope.() -> T): T =
    withContext(default, block)

suspend fun <T> AppDispatchers.withIo(block: suspend CoroutineScope.() -> T): T =
    withContext(io, block)

suspend fun <T> AppDispatchers.withMain(block: suspend CoroutineScope.() -> T): T =
    withContext(main, block)
