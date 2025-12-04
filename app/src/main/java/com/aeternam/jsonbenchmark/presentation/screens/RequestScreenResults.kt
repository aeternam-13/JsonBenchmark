package com.aeternam.jsonbenchmark.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.aeternam.jsonbenchmark.domain.model.RequestMode
import com.aeternam.jsonbenchmark.domain.model.Results
import com.aeternam.jsonbenchmark.presentation.RequestScreenStateHolder

@Composable
fun RequestScreenResults(results: Results, mode: RequestMode) {

    Column {
        Text("${mode} test results ")
        Text("Success: ${results.success}")
        Text("Failures: ${results.failures}")
        Text("Total: ${results.requestResult.size}")
    }
}