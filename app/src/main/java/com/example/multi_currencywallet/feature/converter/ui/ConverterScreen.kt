package com.example.multi_currencywallet.feature.converter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.multi_currencywallet.core.components.CurrencyPickerSheet
import com.example.multi_currencywallet.feature.converter.ui.components.AddToFavoritesButton
import com.example.multi_currencywallet.feature.converter.ui.components.ConversionCard
import com.example.multi_currencywallet.feature.converter.ui.components.ConverterHeader
import com.example.multi_currencywallet.feature.converter.ui.components.QuickAmountSection

@Composable
fun ConverterScreen(
    modifier: Modifier = Modifier,
    viewModel: ConverterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var selectedQuickAmount by remember { mutableStateOf(1000) }
    var showSheet by remember { mutableStateOf(false) }
    var selectingForSend by remember { mutableStateOf(true) }

    val quickAmounts = listOf(10, 50, 100, 500, 1000)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        ConverterHeader()
        Spacer(modifier = Modifier.height(24.dp))

        ConversionCard(
            amountToSend = state.amount,
            onAmountChange = viewModel::onAmountChange,
            fromCurrency = state.from,
            toCurrency = state.to,
            resultText = when {
                state.isLoading -> "..."
                state.result != null -> "%,.2f".format(state.result)
                else -> "--"
            },
            rateText = state.rate?.let {
                "1 ${state.from.code} = ${"%.4f".format(it)} ${state.to.code}"
            } ?: "",
            onSelectFromCurrency = {
                selectingForSend = true
                if (state.currencies.isEmpty()) viewModel.loadCurrencies()
                showSheet = true
            },
            onSelectToCurrency = {
                selectingForSend = false
                if (state.currencies.isEmpty()) viewModel.loadCurrencies()
                showSheet = true
            },
            onSwapCurrencies = viewModel::onSwap
        )

        Spacer(modifier = Modifier.height(12.dp))

        state.failure?.let {
            Text(text = it.message, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        QuickAmountSection(
            quickAmounts = quickAmounts,
            selectedQuickAmount = selectedQuickAmount,
            onAmountSelected = { value ->
                selectedQuickAmount = value
                viewModel.onAmountChange(value.toString())
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        AddToFavoritesButton(
            isSaved = state.isSaved,
            onClick = viewModel::onToggleFavorite
        )

        Spacer(modifier = Modifier.height(10.dp))
    }

    if (showSheet) {
        CurrencyPickerSheet(
            currencies = state.currencies,
            onCurrencySelected = { currency ->
                if (selectingForSend) viewModel.onFromChange(currency)
                else viewModel.onToChange(currency)
                showSheet = false
            },
            onDismiss = { showSheet = false }
        )
    }
}