package com.loanmanager.customerservice.services

import com.loanmanager.customerservice.dtos.CustomerCreationResponse
import com.loanmanager.customerservice.dtos.CustomerRequestDto
import com.loanmanager.customerservice.dtos.UserServiceResponse
import com.loanmanager.customerservice.exceptions.UserNotFoundException
import com.loanmanager.customerservice.repositories.CustomersRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CustomersServiceTest {

    @Mock
    private lateinit var customersRepository: CustomersRepository

    @Mock
    private lateinit var userService: UsersService

    @InjectMocks
    private lateinit var customersService: CustomersService

    @Test
    fun `create() should save the customer and return id in response`() {
        val userId = 345L
        val customerRequest = CustomerRequestDto(
                userId = userId,
                firstName = "John",
                lastName = "Smith",
                email = "johnsmith@example.com",
                phone = "+49 123 456 78 910"
        )
        val savedEntityId = 123L
        val customer = customerRequest.mapToDomain()
        val customerCreationResponse = CustomerCreationResponse(id = savedEntityId)
        val userResponse = UserServiceResponse(
                id = userId,
                email = customerRequest.email,
                password = "somePassword",
                role = "ADMIN"
        )
        doReturn(userResponse).`when`(userService).getUserById(userId)
        doReturn(customer.copy(id = savedEntityId)).`when`(customersRepository).save(customer)

        val result = customersService.create(customerRequest)

        assertEquals(customerCreationResponse, result)
        verify(userService, times(1)).getUserById(userId)
        verify(customersRepository, times(1)).save(customer)
    }

    @Test
    fun `create() should throw UserNotFoundException when no user found for given user id`() {
        val userId = 345L
        val customerRequest = CustomerRequestDto(
                userId = userId,
                firstName = "John",
                lastName = "Smith",
                email = "johnsmith@example.com",
                phone = "+49 123 456 78 910"
        )
        doReturn(null).`when`(userService).getUserById(userId)

        val result = assertThrows<UserNotFoundException> { customersService.create(customerRequest) }

        assertEquals("No user found for given id $userId", result.message)
        verify(userService, times(1)).getUserById(userId)
        verifyNoMoreInteractions(customersRepository)
    }
}
