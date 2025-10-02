package com.va.liontravel_test.presentation

/**
 * Created by AidenChang on 2025/10/2
 */
data class TravelUiState(
    val isLoading: Boolean = true,
    val images: List<String>? = null,
    val error: String? = null
)
