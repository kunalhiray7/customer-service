package com.loanmanager.customerservice.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Customer(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @get: NotNull
        val userId: Long = -1L,

        @get: NotBlank
        val firstName: String = "",

        @get: NotBlank
        val lastName: String = "",

        @get: NotBlank
        val email: String = "",

        @get: NotBlank
        val phone: String = ""
)
