package com.va.liontravel_test.presentation

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.va.liontravel_test.data.repo.AssetSaveRepository
import kotlinx.coroutines.delay
import okhttp3.HttpUrl.Companion.toHttpUrl

/**
 * Created by AidenChang on 2025/10/2
 */

@Composable
fun CarouselScreenRoot() {
    val context = LocalContext.current

    val vm: CarouselViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                CarouselViewModel(
                    repo = AssetSaveRepository(context.applicationContext)
                )
            }
        }
    )

    val uiState by vm.state.collectAsStateWithLifecycle()
    CarouselScreen(state = uiState)
}

@Composable
fun CarouselScreen(
    state: CarouselUiState,
    modifier: Modifier = Modifier
) {

    when {
        state.isLoading -> {
            println("aiden isLoading")
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        state.error != null -> {
            println("aiden error")
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${state.error}")
            }
        }
        state.images.isEmpty() -> {
            println("aiden images.isEmpty")
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No images")
            }
        }
        else -> {
            // 真實資料數量
            val realCount = state.images.size

            // 虛擬無限長度
            val infiniteCount = Int.MAX_VALUE

            // 從中間開始，避免一開始就靠近邊界
            val startPage = remember(realCount) {
                val mid = infiniteCount / 2
                println("aiden mid: ${mid}")
                mid - (mid % realCount) // 對齊到 realCount 的倍數
            }

            val pagerState = rememberPagerState(
                initialPage = startPage,
                pageCount = { infiniteCount }
            )

            // 是否正在手動拖曳
            val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

            // 自動輪播（只有沒手勢時才啟動）
            if (!isDragged) {
                var tick by remember { mutableStateOf(0) }
                LaunchedEffect(tick, realCount) {
                    delay(3000)
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    tick++ // 觸發下一輪
                }
            }

            Box(
                modifier.fillMaxSize()
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                ) { virtualPage ->
                    val index = virtualPage % realCount
                    val url = state.images[index]
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(url.url.toHttpUrl())
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CarouselScreenPreview() {
//    CarouselScreen()
}