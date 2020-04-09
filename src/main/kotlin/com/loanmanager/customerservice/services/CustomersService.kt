package com.loanmanager.customerservice.services

import com.loanmanager.customerservice.dtos.CustomerCreationResponse
import com.loanmanager.customerservice.dtos.CustomerRequestDto
import com.loanmanager.customerservice.dtos.CustomerResponse
import com.loanmanager.customerservice.exceptions.CustomerNotFound
import com.loanmanager.customerservice.exceptions.UserNotFoundException
import com.loanmanager.customerservice.repositories.CustomersRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomersService(private val customersRepository: CustomersRepository,
                       private val usersService: UsersService) {

    companion object {
        private val logger = LoggerFactory.getLogger(CustomersService::class.java)
    }

    @Throws(UserNotFoundException::class)
    fun create(customerRequest: CustomerRequestDto): CustomerCreationResponse {
        logger.info("Creating customer with email ${customerRequest.email}")

        usersService.getUserById(customerRequest.userId)
                ?: throw UserNotFoundException("No user found for given id ${customerRequest.userId}")
        val savedCustomer = customersRepository.save(customerRequest.mapToDomain())

        logger.info("Created customer with email ${customerRequest.email} successfully!")
        return CustomerCreationResponse(id = savedCustomer.id!!)
    }

    @Throws(CustomerNotFound::class)
    fun getById(customerId: Long): CustomerResponse {
        logger.info("Fetching customer detail for id: $customerId")

        val customer = customersRepository.findById(customerId)
                .orElseThrow { CustomerNotFound("No customer found for given id $customerId") }

        logger.info("Fetched customer detail for id: $customerId successfully")
        return CustomerResponse.of(customer)
    }

}
