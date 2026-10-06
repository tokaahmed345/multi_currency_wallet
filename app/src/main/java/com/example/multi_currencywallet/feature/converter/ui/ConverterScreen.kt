package com.example.multi_currencywallet.feature.converter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.multi_currencywallet.core.components.CurrencyPickerSheet
import com.example.multi_currencywallet.feature.converter.ui.components.AddToFavoritesButton
import com.example.multi_currencywallet.feature.converter.ui.components.ConversionCard
import com.example.multi_currencywallet.feature.converter.ui.components.ConverterHeader
import com.example.multi_currencywallet.feature.converter.ui.components.QuickAmountSection
import com.example.multi_currencywallet.feature.model.fakeCurrencies

@Composable
fun ConverterScreen(modifier: Modifier = Modifier) {
    var amountToSend by remember { mutableStateOf("1000") }
    var selectedQuickAmount by remember { mutableStateOf(1000) }
    var fromCurrency by remember { mutableStateOf(fakeCurrencies[0]) }
    var toCurrency by remember { mutableStateOf(fakeCurrencies[1]) }
    var showSheet by remember { mutableStateOf(false) }
    var selectingForSend by remember { mutableStateOf(true) }
    var isSaved by remember { mutableStateOf(false) }

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
            amountToSend = amountToSend,
            onAmountChange = { amountToSend = it },
            fromCurrency = fromCurrency,
            toCurrency = toCurrency,
            onSelectFromCurrency = {
                selectingForSend = true
                showSheet = true
            },
            onSelectToCurrency = {
                selectingForSend = false
                showSheet = true
            },
            onSwapCurrencies = {
                val temp = fromCurrency
                fromCurrency = toCurrency
                toCurrency = temp
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        QuickAmountSection(
            quickAmounts = quickAmounts,
            selectedQuickAmount = selectedQuickAmount,
            onAmountSelected = { value ->
                selectedQuickAmount = value
                amountToSend = value.toString()
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        AddToFavoritesButton(
            isSaved = isSaved,
            onClick = { isSaved = !isSaved }
        )

        Spacer(modifier = Modifier.height(10.dp))
    }

    if (showSheet) {
        CurrencyPickerSheet(
            currencies = fakeCurrencies,
            onCurrencySelected = { currency ->
                if (selectingForSend) fromCurrency = currency else toCurrency = currency
                showSheet = false
            },
            onDismiss = { showSheet = false }
        )
    }
}