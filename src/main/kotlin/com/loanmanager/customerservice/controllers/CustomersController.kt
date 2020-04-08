package com.loanmanager.customerservice.controllers

import com.loanmanager.customerservice.dtos.CustomerRequestDto
import com.loanmanager.customerservice.services.CustomersService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class CustomersController(private val customersService: CustomersService) {

    @PostMapping("/api/customers")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody customerRequest: CustomerRequestDto) = customersService.create(customerRequest)

    @GetMapping("/api/customers/{customerId}")
    fun getById(@PathVariable("customerId") customerId: Long) = customersService.getById(customerId)
}
