package com.example.multi_currencywallet.core.error


sealed class Failure(open val message: String) {
    data object NoInternet : Failure("No internet connection")
    data object Timeout : Failure("Request timed out")
    data object EmptyData : Failure("No data found for this currency pair")
    data class Server(val code: Int) : Failure("Server error ($code)")
    data class Unknown(override val message: String = "Something went wrong") : Failure(message)
}