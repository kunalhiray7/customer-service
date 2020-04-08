package com.loanmanager.customerservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.loanmanager.customerservice.clients.UsersServiceClient
import com.loanmanager.customerservice.utils.ObjectMapperUtil.Companion.getObjectMapper
import feign.Feign
import feign.Logger
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.okhttp.OkHttpClient
import feign.slf4j.Slf4jLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class CustomerServiceApplication {

	@Value("\${users.service.url}")
	private lateinit var usersServiceUrl: String

	@Bean
	@Primary
	fun serializingObjectMapper(): ObjectMapper {
		return getObjectMapper()
	}

	@Bean
	fun usersServiceClient(): UsersServiceClient {
		return Feign.builder()
				.client(OkHttpClient())
				.encoder(JacksonEncoder(getObjectMapper()))
				.decoder(JacksonDecoder(getObjectMapper()))
				.logger(Slf4jLogger(UsersServiceClient::class.java))
				.logLevel(Logger.Level.FULL)
				.target(UsersServiceClient::class.java, usersServiceUrl)
	}
}

fun main(args: Array<String>) {
	runApplication<CustomerServiceApplication>(*args)
}
