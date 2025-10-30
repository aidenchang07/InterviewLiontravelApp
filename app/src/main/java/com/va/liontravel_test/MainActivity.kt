package com.va.liontravel_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.va.liontravel_test.presentation.TravelScreenRoot
import com.va.liontravel_test.presentation.TravelViewModel
import com.va.liontravel_test.ui.theme.Liontravel_testTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<TravelViewModel>()

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