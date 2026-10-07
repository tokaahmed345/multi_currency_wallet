package com.example.multi_currencywallet.feature.converter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multi_currencywallet.core.model.Currency
import com.example.multi_currencywallet.feature.converter.domain.usecase.ConvertCurrencyUseCase
import com.example.multi_currencywallet.feature.converter.domain.usecase.GetCurrenciesUseCase
import com.example.multi_currencywallet.feature.converter.domain.usecase.GetExchangeRateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val getRate: GetExchangeRateUseCase,
    private val convert: ConvertCurrencyUseCase,
    private val getCurrencies: GetCurrenciesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ConverterUiState())
    val state: StateFlow<ConverterUiState> = _state.asStateFlow()

    init {
        loadCurrencies()
        loadRate()
    }

    fun loadCurrencies() {
        viewModelScope.launch {
            getCurrencies().fold(
                onLeft = { failure ->
                    _state.update { it.copy(failure = failure) }
                },
                onRight = { list ->
                    _state.update { it.copy(currencies = list) }
                }
            )
        }
    }

    fun onAmountChange(value: String) {
        _state.update { it.copy(amount = value) }
        recalculate()
    }

    fun onFromChange(currency: Currency) {
        _state.update { it.copy(from = currency) }
        loadRate()
    }

    fun onToChange(currency: Currency) {
        _state.update { it.copy(to = currency) }
        loadRate()
    }

    fun onSwap() {
        _state.update { it.copy(from = it.to, to = it.from) }
        loadRate()
    }

    fun loadRate() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, failure = null) }
            val s = _state.value
            val result = getRate(s.from.code, s.to.code)
            result.fold(
                onLeft = { failure ->
                    _state.update { it.copy(isLoading = false, failure = failure) }
                },
                onRight = { exchangeRate ->
                    _state.update { it.copy(isLoading = false, rate = exchangeRate.rate) }
                    recalculate()
                }
            )
        }
    }

    private fun recalculate() {
        val s = _state.value
        val amount = s.amount.toDoubleOrNull()
        val rate = s.rate
        _state.update {
            it.copy(result = if (amount != null && rate != null) convert(amount, rate) else null)
        }
    }
}