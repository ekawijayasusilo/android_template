package com.template.core.testing.koin

import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.module

class KoinTestRuleTest {
    private val testModule = module { single { TestService() } }

    @get:Rule val koinRule = KoinTestRule(testModule)

    @Test
    fun ruleStartsKoinForTest() {
        assertNotNull(koinRule.koin.get<TestService>())
    }
}

class CheckModulesTest {
    @Test
    fun checkModulesResolvesDeclaredRoots() {
        checkModules(module { single { TestService() } }, rootTypes = listOf(TestService::class))
    }
}

private class TestService
