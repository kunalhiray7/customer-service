package com.loanmanager.customerservice.repositories

import com.loanmanager.customerservice.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomersRepository: JpaRepository<Customer, Long>
