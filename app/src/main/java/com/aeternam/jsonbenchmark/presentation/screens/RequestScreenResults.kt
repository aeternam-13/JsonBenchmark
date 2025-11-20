package com.aeternam.jsonbenchmark.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.aeternam.jsonbenchmark.presentation.RequestScreenStateHolder

@Composable
fun RequestScreenResults(stateHolder: RequestScreenStateHolder) {
    val results = stateHolder.results
    Column {
        Text("${stateHolder.requestMode} test results ")
        Text("Success: ${results.success}")
        Text("Failures: ${results.failures}")
        Text("Total: ${results.requestResult.size}")
    }
}