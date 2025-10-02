package com.va.liontravel_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.va.liontravel_test.presentation.TravelScreenRoot
import com.va.liontravel_test.presentation.TravelViewModel
import com.va.liontravel_test.ui.theme.Liontravel_testTheme

class MainActivity : ComponentActivity() {
    private val viewModel: TravelViewModel by viewModels { TravelViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Liontravel_testTheme {
                TravelScreenRoot(viewModel)
            }
        }
    }
}