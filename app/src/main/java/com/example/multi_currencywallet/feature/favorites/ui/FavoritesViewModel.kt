package com.example.multi_currencywallet.feature.favorites.ui
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multi_currencywallet.feature.favorites.domain.entity.FavoritePair
import com.example.multi_currencywallet.feature.favorites.domain.usecase.ObserveFavoritesUseCase
import com.example.multi_currencywallet.feature.favorites.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    observeFavorites: ObserveFavoritesUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
) : ViewModel() {

    val favorites: StateFlow<List<FavoritePair>> =
        observeFavorites().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun remove(pair: FavoritePair) {
        viewModelScope.launch { toggleFavorite(pair.base, pair.target, pair.rate) }
    }

    fun undo(pair: FavoritePair) {
        viewModelScope.launch { toggleFavorite(pair.base, pair.target, pair.rate) }
    }
}