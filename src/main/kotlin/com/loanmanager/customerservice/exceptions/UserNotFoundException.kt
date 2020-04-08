package com.loanmanager.customerservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class UserNotFoundException(override val message: String?) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = -5630090486720292433L
    }
}
