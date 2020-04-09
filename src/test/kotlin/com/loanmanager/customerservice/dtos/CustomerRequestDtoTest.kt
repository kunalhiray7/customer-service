package com.loanmanager.customerservice.dtos

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.validation.Validation
import javax.validation.Validator

class CustomerRequestDtoTest {

    private lateinit var validator: Validator

    private val customerRequestDto = CustomerRequestDto(
            userId = 123L,
            firstName = "John",
            lastName = "Smith",
            email = "johnsmith@example.com",
            phone = "+49 1234567890"
    )

    @BeforeEach
    internal fun setUp() {
        val validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.validator
    }

    @Test
    fun `should not have validation error if request is valid`() {
        val validations = validator.validate(customerRequestDto)

        assertEquals(0, validations.size)
    }

    @Test
    fun `should have validation error if first name is blank`() {
        val request = customerRequestDto.copy(firstName = "")

        val validations = validator.validate(request)

        assertEquals(1, validations.size)
    }

    @Test
    fun `should have validation error if last name is blank`() {
        val request = customerRequestDto.copy(lastName = "")

        val validations = validator.validate(request)

        assertEquals(1, validations.size)
    }

    @Test
    fun `should have validation error if email is blank`() {
        val request = customerRequestDto.copy(email = "")

        val validations = validator.validate(request)

        assertEquals(1, validations.size)
    }

    @Test
    fun `should have validation error if phone is blank`() {
        val request = customerRequestDto.copy(phone = "")

        val validations = validator.validate(request)

        assertEquals(1, validations.size)
    }
}
