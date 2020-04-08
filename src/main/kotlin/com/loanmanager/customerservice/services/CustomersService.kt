package com.loanmanager.customerservice.services

import com.loanmanager.customerservice.dtos.CustomerCreationResponse
import com.loanmanager.customerservice.dtos.CustomerRequestDto
import com.loanmanager.customerservice.exceptions.UserNotFoundException
import com.loanmanager.customerservice.repositories.CustomersRepository
import org.springframework.stereotype.Service

@Service
class CustomersService(private val customersRepository: CustomersRepository,
                       private val usersService: UsersService) {
    fun create(customerRequest: CustomerRequestDto): CustomerCreationResponse {
        usersService.getUserById(customerRequest.userId)
                ?: throw UserNotFoundException("No user found for given id ${customerRequest.userId}")
        val savedEntity = customersRepository.save(customerRequest.mapToDomain())
        return CustomerCreationResponse(id = savedEntity.id!!)
    }

}
