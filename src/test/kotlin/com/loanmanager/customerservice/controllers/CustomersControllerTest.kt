package com.loanmanager.customerservice.controllers

import com.loanmanager.customerservice.dtos.CustomerCreationResponse
import com.loanmanager.customerservice.dtos.CustomerRequestDto
import com.loanmanager.customerservice.dtos.CustomerResponse
import com.loanmanager.customerservice.exceptions.CustomerNotFound
import com.loanmanager.customerservice.exceptions.UserNotFoundException
import com.loanmanager.customerservice.services.CustomersService
import com.loanmanager.customerservice.utils.ObjectMapperUtil
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
class CustomersControllerTest {

    private val mapper = ObjectMapperUtil.getObjectMapper()

    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var customersService: CustomersService

    @InjectMocks
    private lateinit var customersController: CustomersController

    @BeforeEach
    internal fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customersController).build()
    }

    @Test
    fun `POST should create the customers and return 201`() {
        val customerRequest = CustomerRequestDto(
                userId = 1L,
                firstName = "John",
                lastName = "Smith",
                email = "johnsmith@example.com",
                phone = "+49 123 456 78 910"
        )
        val customerCreationResponse = CustomerCreationResponse(id = 11L)
        doReturn(customerCreationResponse).`when`(customersService).create(customerRequest)

        mockMvc.perform(post("/api/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(customerRequest)))
                .andExpect(status().isCreated)
                .andExpect(content().string(mapper.writeValueAsString(customerCreationResponse)))

        verify(customersService, times(1)).create(customerRequest)
    }

    @Test
    fun `POST should return 400 when request is invalid`() {
        val customerRequest = CustomerRequestDto(
                userId = 1L,
                firstName = "",
                lastName = "Smith",
                email = "johnsmith@example.com",
                phone = "+49 123 456 78 910"
        )
        val customerCreationResponse = CustomerCreationResponse(id = 11L)
        doReturn(customerCreationResponse).`when`(customersService).create(customerRequest)

        mockMvc.perform(post("/api/customers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(customerRequest)))
                .andExpect(status().isBadRequest)

        verifyNoMoreInteractions(customersService)
    }

    @Test
    fun `POST should return 404 when no user found for given userId`() {
        val customerRequest = CustomerRequestDto(
                userId = 1L,
                firstName = "John",
                lastName = "Smith",
                email = "johnsmith@example.com",
                phone = "+49 123 456 78 910"
        )
        doThrow(UserNotFoundException("User not found")).`when`(customersService).create(customerRequest)

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(customerRequest)))
                .andExpect(status().isNotFound)

        verify(customersService, times(1)).create(customerRequest)
    }

    @Test
    fun `GET should return the customer for given id`() {
        val customerId = 123L
        val customerResponse = CustomerResponse(
                id = customerId,
                userId = 1L,
                firstName = "John",
                lastName = "Smith",
                email = "johnsmith@example.com",
                phone = "+49 123 456 78 910"
        )
        doReturn(customerResponse).`when`(customersService).getById(customerId)

        mockMvc.perform(get("/api/customers/$customerId"))
                .andExpect(status().isOk)
                .andExpect(content().string(mapper.writeValueAsString(customerResponse)))

        verify(customersService, times(1)).getById(customerId)
    }

    @Test
    fun `GET should return 404 when customer not found for given id`() {
        val customerId = 123L
        doThrow(CustomerNotFound("Customer not found")).`when`(customersService).getById(customerId)

        mockMvc.perform(get("/api/customers/$customerId"))
                .andExpect(status().isNotFound)

        verify(customersService, times(1)).getById(customerId)
    }
}
