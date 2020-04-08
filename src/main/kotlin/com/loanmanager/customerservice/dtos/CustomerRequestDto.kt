package com.loanmanager.customerservice.dtos

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CustomerRequestDto(
        @get: NotNull
        val userId: Long,

        @get: NotBlank
        val firstName: String,

        @get: NotBlank
        val lastName: String,

        @get: NotBlank
        val email: String,

        @get: NotBlank
        val phone: String
)
