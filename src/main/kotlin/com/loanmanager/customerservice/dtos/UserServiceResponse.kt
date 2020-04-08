package com.loanmanager.customerservice.dtos

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserServiceResponse(
        val id: Long,
        val email: String,
        val password: String,
        val role: String
)
