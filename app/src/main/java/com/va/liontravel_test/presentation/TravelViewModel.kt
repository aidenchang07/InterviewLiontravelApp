package com.va.liontravel_test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.va.liontravel_test.MyApp
import com.va.liontravel_test.domain.repo.TravelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by AidenChang on 2025/10/2
 */
class TravelViewModel(
    private val repo: TravelRepository
) : ViewModel() {
    private val _state = MutableStateFlow(TravelUiState())
    val state: StateFlow<TravelUiState> = _state.asStateFlow()

    init { load() }

    companion object Companion {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repo = (this[APPLICATION_KEY] as MyApp).container.travelRepository
                TravelViewModel(repo)
            }
        }
    }

    fun load() {
        viewModelScope.launch {
            _state.value = TravelUiState(isLoading = true)
            runCatching { repo.getHomeData() }
                .onSuccess { current ->
                    val keyTagNo= current.tagData.first().tagNo
                    val images = current.draftData
                        .filter { it.tagNo == keyTagNo }
                        .map { it.draftPic }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            images = images
                        )
                    }
                }.onFailure { current ->
                    _state.update {
                        it.copy(error = current.message)
                    }
                }
        }
    }
}