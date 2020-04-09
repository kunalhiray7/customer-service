package com.loanmanager.customerservice.utils

import com.loanmanager.customerservice.utils.ObjectMapperUtil.Companion.getObjectMapper
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ObjectMapperUtilTest {

    @Test
    fun `objectMapper should have Kotlin module`() {
        val objectMapper = getObjectMapper()

        val registeredModuleIds = objectMapper.registeredModuleIds

        assertTrue(registeredModuleIds.contains("com.fasterxml.jackson.module.kotlin.KotlinModule"))
    }
}
