package com.loanmanager.customerservice.services

import com.loanmanager.customerservice.clients.UsersServiceClient
import com.loanmanager.customerservice.dtos.UserServiceResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.web.util.UriComponentsBuilder

@ExtendWith(MockitoExtension::class)
class UsersServiceTest {

    private val userServiceUrl = "http://user-service.com"

    @Mock
    private lateinit var usersServiceClient: UsersServiceClient

    private lateinit var usersService: UsersService

    @BeforeEach
    internal fun setUp() {
        usersService = UsersService(usersServiceClient, userServiceUrl)
    }

    @Test
    fun `getUserById() should return the user for given userId`() {
        val userId = 123L
        val user = UserServiceResponse(
                id = userId,
                email = "johnsmith@example.com",
                password = "somePassword",
                role = "ADMIN"
        )
        val uri = UriComponentsBuilder.newInstance().path("$userServiceUrl/api/users/$userId").build().toUri()
        doReturn(user).`when`(usersServiceClient).getUserById(uri)

        val result = usersService.getUserById(userId)

        assertEquals(user, result)
        verify(usersServiceClient, times(1)).getUserById(uri)
    }

    @Test
    fun `getUserById() should return null if any error occurs while getting user`() {
        val userId = 123L
        val uri = UriComponentsBuilder.newInstance().path("$userServiceUrl/api/users/$userId").build().toUri()
        doThrow(RuntimeException("Something went wrong")).`when`(usersServiceClient).getUserById(uri)

        val result = usersService.getUserById(userId)

        assertEquals(null, result)
        verify(usersServiceClient, times(1)).getUserById(uri)
    }
}
