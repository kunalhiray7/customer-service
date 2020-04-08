package com.loanmanager.customerservice.clients

import com.loanmanager.customerservice.dtos.UserServiceResponse
import feign.Headers
import feign.RequestLine
import java.net.URI

interface UsersServiceClient {

    @RequestLine("GET")
    @Headers("Content-Type: application/json")
    fun getUserById(uri: URI): UserServiceResponse
}
