package com.loanmanager.customerservice.dtos

import com.loanmanager.customerservice.domain.Customer

data class CustomerResponse(
        val id: Long,

        val userId: Long,

        val firstName: String,

        val lastName: String,

        val email: String,

        val phone: String
) {
    companion object {
        fun of(customer: Customer) = CustomerResponse(
                id = customer.id!!,
                userId = customer.userId,
                firstName = customer.firstName,
                lastName = customer.lastName,
                email = customer.email,
                phone = customer.phone
        )
    }
}
