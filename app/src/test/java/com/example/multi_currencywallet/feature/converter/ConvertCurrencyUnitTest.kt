package com.example.multi_currencywallet.feature.converter
class ConvertCurrencyUseCaseTest {
    private val useCase = ConvertCurrencyUseCase()

    @Test
    fun `converts amount using rate`() {
        assertEquals(48130.0, useCase(1000.0, 48.13), 0.001)
    }

    @Test
    fun `zero amount returns zero`() {
        assertEquals(0.0, useCase(0.0, 48.13), 0.001)
    }
}