package com.aeternam.jsonbenchmark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aeternam.jsonbenchmark.presentation.screens.RequestScreen
import com.aeternam.jsonbenchmark.ui.theme.JsonBenchmarkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JsonBenchmarkTheme {
                RequestScreen()
            }
        }
    }
}