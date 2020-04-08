package com.loanmanager.customerservice.dtos

data class CustomerResponse(
        val id: Long,

        val userId: Long,

        val firstName: String,

        val lastName: String,

        val email: String,

        val phone: String
)
