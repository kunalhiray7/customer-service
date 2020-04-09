package com.loanmanager.customerservice.services

import com.loanmanager.customerservice.clients.UsersServiceClient
import com.loanmanager.customerservice.dtos.UserServiceResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class UsersService(private val usersServiceClient: UsersServiceClient,
                   @Value("\${users.service.url}")
                   private val userServiceUrl: String) {

    companion object {
        private val logger = LoggerFactory.getLogger(UsersService::class.java)!!
    }

    fun getUserById(userId: Long): UserServiceResponse? {
        logger.info("Calling user-service to fetch user for id: $userId")
        val uri = UriComponentsBuilder.newInstance().path("$userServiceUrl/api/users/$userId").build().toUri()
        return try {
            usersServiceClient.getUserById(uri)
        } catch (e: Throwable) {
            logger.info("Not able to fetch user with id $userId::", e)
            null
        }
    }

}
