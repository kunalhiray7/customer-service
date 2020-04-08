package com.loanmanager.customerservice.services

import com.loanmanager.customerservice.dtos.CustomerCreationResponse
import com.loanmanager.customerservice.dtos.CustomerRequestDto
import com.loanmanager.customerservice.repositories.CustomersRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CustomersServiceTest {

    @Mock
    private lateinit var customersRepository: CustomersRepository

    @InjectMocks
    private lateinit var customersService: CustomersService

    @Test
    fun `create() should save the customer and return id in response`() {
        val customerRequest = CustomerRequestDto(
                userId = 1L,
                firstName = "John",
                lastName = "Smith",
                email = "johnsmith@example.com",
                phone = "+49 123 456 78 910"
        )
        val savedEntityId = 123L
        val customer = customerRequest.mapToDomain()
        val customerCreationResponse = CustomerCreationResponse(id = savedEntityId)
        doReturn(customer.copy(id = savedEntityId)).`when`(customersRepository).save(customer)

        val result = customersService.create(customerRequest)

        assertEquals(customerCreationResponse, result)
        verify(customersRepository, times(1)).save(customer)
    }
}
