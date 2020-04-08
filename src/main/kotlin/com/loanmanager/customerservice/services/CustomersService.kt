package com.loanmanager.customerservice.services

import com.loanmanager.customerservice.dtos.CustomerCreationResponse
import com.loanmanager.customerservice.dtos.CustomerRequestDto
import com.loanmanager.customerservice.repositories.CustomersRepository
import org.springframework.stereotype.Service

@Service
class CustomersService(private val customersRepository: CustomersRepository) {
    fun create(customerRequest: CustomerRequestDto): CustomerCreationResponse {
        val savedEntity = customersRepository.save(customerRequest.mapToDomain())
        return CustomerCreationResponse(id = savedEntity.id!!)
    }

}
