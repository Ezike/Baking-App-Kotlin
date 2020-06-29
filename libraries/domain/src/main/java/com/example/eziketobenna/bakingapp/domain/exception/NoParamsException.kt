package com.example.eziketobenna.bakingapp.domain.exception

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

internal class NoParamsException(errorMessage: String = "Your params cannot be null for this use case") :
    IllegalArgumentException(errorMessage)

@OptIn(ExperimentalContracts::class)
fun <T : Any> requireParams(value: T?): T {
    contract {
        returns() implies (value != null)
    }

    if (value == null) {
        throw NoParamsException()
    } else {
        return value
    }
}
