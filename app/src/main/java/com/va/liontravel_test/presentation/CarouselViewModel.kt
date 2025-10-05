package com.va.liontravel_test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.va.liontravel_test.domain.model.CarouselImage
import com.va.liontravel_test.domain.repo.SaveRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Created by AidenChang on 2025/10/2
 */
class CarouselViewModel(
    private val repo: SaveRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CarouselUiState())
    val state: StateFlow<CarouselUiState> = _state.asStateFlow()

    init { load() }

    fun load() {
        viewModelScope.launch {
            _state.value = CarouselUiState(isLoading = true)
            runCatching { repo.getHomeData() }
                .onSuccess { current ->
                    val keyTagNo= current.tagData.first().tagNo
                    val images = current.draftData
                        .filter { it.tagNo == keyTagNo }
                        .map { CarouselImage(it.draftPic) }
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