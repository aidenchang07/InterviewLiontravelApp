package com.va.liontravel_test.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay

/**
 * Created by AidenChang on 2025/10/2
 */

@Composable
fun TravelScreenRoot(vm: TravelViewModel) {
    val uiState by vm.state.collectAsStateWithLifecycle()
    TravelScreen(state = uiState)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TravelScreen(
    state: TravelUiState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.error != null -> {
                Text(text = "Error: ${state.error}")
            }
            state.images.isNullOrEmpty() -> {
                Text("No images")
            }
            else -> {
                // 真實資料數量
                val realCount = state.images.size

                // 虛擬無限長度
                val infiniteCount = Int.MAX_VALUE

                // 從中間開始，避免一開始就靠近邊界
                val startPage = remember(realCount) {
                    val mid = infiniteCount / 2
                    mid - (mid % realCount) // 對齊到 realCount 的倍數
                }

                val pagerState = rememberPagerState(
                    initialPage = startPage,
                    pageCount = { infiniteCount }
                )

                // 是否正在手動拖曳
                val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
                val pressSource = remember { MutableInteractionSource() }
                // 是否正在點擊不動
                val isPressed by pressSource.collectIsPressedAsState()

                // 自動輪播（只有沒手勢時才啟動）
                if (!isDragged && !isPressed) {
                    LaunchedEffect(realCount) {
                        while (true) {
                            delay(3000)
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            interactionSource = pressSource,
                            indication = null, // 不需要水波紋可設 null
                            onClick = {}
                        )
                ) { virtualPage ->
                    val index = virtualPage % realCount
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(state.images[index])
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TravelScreenPreview() {
    TravelScreen(
        state = TravelUiState()
    )
}