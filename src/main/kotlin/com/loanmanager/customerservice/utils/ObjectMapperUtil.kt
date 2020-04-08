package com.loanmanager.customerservice.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class ObjectMapperUtil {

    companion object {
        fun getObjectMapper(): ObjectMapper {
            val objectMapper = ObjectMapper()

            objectMapper.registerModule(KotlinModule())

            return objectMapper
        }

    }

}
