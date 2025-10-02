package com.va.liontravel_test.presentation

import com.va.liontravel_test.domain.model.CarouselImage

/**
 * Created by AidenChang on 2025/10/2
 */
data class CarouselUiState(
    val isLoading: Boolean = true,
    val images: List<CarouselImage> = emptyList(),
    val error: String? = null
)
