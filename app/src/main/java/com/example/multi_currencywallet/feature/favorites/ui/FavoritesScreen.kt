package com.example.multi_currencywallet.feature.favorites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.multi_currencywallet.core.model.Currency
import com.example.multi_currencywallet.core.util.CurrencyMapper
import com.example.multi_currencywallet.feature.favorites.ui.components.EmptyFavorites
import com.example.multi_currencywallet.feature.favorites.ui.components.PairCard
import kotlinx.coroutines.launch

data class FavoritePair(
    val from: Currency,
    val to: Currency,
    val rate: Double,
    val change: String
) {
    val id: String get() = "${from.code}${to.code}"
}

private fun cur(code: String) = CurrencyMapper.fromCode(code)

private fun initialPairs() = listOf(
    FavoritePair(cur("USD"), cur("EUR"), 0.89, "+0.12%"),
    FavoritePair(cur("EUR"), cur("GBP"), 0.87, "+0.08%"),
    FavoritePair(cur("GBP"), cur("USD"), 1.31, "+0.21%"),
    FavoritePair(cur("USD"), cur("JPY"), 149.50, "+0.05%"),
    FavoritePair(cur("EUR"), cur("CHF"), 0.94, "+0.10%")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onGoToConverter: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pairs = remember { mutableStateListOf<FavoritePair>().apply { addAll(initialPairs()) } }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Favorites",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Saved for offline use",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (pairs.isEmpty()) {
                EmptyFavorites(onGoToConverter = onGoToConverter)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(pairs, key = { it.id }) { pair ->
                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = { value ->
                                if (value == SwipeToDismissBoxValue.EndToStart) {
                                    val index = pairs.indexOf(pair)
                                    pairs.remove(pair)
                                    scope.launch {
                                        val result = snackbarHostState.showSnackbar(
                                            message = "Pair removed",
                                            actionLabel = "Undo"
                                        )
                                        if (result == SnackbarResult.ActionPerformed) {
                                            pairs.add(index.coerceAtMost(pairs.size), pair)
                                        }
                                    }
                                    true
                                } else false
                            }
                        )

                        SwipeToDismissBox(
                            state = dismissState,
                            enableDismissFromStartToEnd = false,
                            backgroundContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(MaterialTheme.colorScheme.error)
                                        .padding(end = 24.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.White
                                    )
                                }
                            }
                        ) {
                            PairCard(pair = pair)
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}