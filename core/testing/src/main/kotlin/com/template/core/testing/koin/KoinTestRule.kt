package com.template.core.testing.koin

import kotlin.reflect.KClass
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module

class KoinTestRule(
    private val modules: List<Module>,
    private val onKoinStarted: Koin.() -> Unit = {},
) : TestWatcher() {
    constructor(vararg modules: Module) : this(modules.toList())

    lateinit var koin: Koin
        private set

    override fun starting(description: Description) {
        koin = startKoin { modules(this@KoinTestRule.modules) }.koin
        koin.onKoinStarted()
    }

    override fun finished(description: Description) {
        stopKoin()
    }
}

fun checkModules(modules: List<Module>, rootTypes: List<KClass<*>> = emptyList()) {
    val application = startKoin { modules(modules) }
    try {
        rootTypes.forEach { rootType -> application.koin.get(clazz = rootType) }
    } finally {
        stopKoin()
    }
}

fun checkModules(vararg modules: Module, rootTypes: List<KClass<*>> = emptyList()) =
    checkModules(modules.toList(), rootTypes)
