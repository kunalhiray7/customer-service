package com.loanmanager.customerservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.loanmanager.customerservice.utils.ObjectMapperUtil
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@SpringBootApplication
class CustomerServiceApplication {

	@Bean
	@Primary
	fun serializingObjectMapper(): ObjectMapper {
		return ObjectMapperUtil.getObjectMapper()
	}
}

fun main(args: Array<String>) {
	runApplication<CustomerServiceApplication>(*args)
}
