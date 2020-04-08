package com.loanmanager.customerservice.controllers

import com.loanmanager.customerservice.dtos.CustomerRequestDto
import com.loanmanager.customerservice.services.CustomersService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class CustomersController(private val customersService: CustomersService) {

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody customerRequest: CustomerRequestDto) = customersService.create(customerRequest)
}
