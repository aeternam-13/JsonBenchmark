package com.aeternam.jsonbenchmark.presentation.request_creator_screen.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aeternam.jsonbenchmark.presentation.request_creator_screen.RequestScreenStateHolder

@Composable
fun RequestScreenGatheringInfo(
    stateHolder: RequestScreenStateHolder,
    onRequestAmountChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = stateHolder.requestAmount.toString(),
            label = { Text("Requests to perform") },
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.all { it.isDigit() }) {

                    onRequestAmountChange(newValue)
                }
            })
        Spacer(Modifier.height(10.dp))
        Button(onClick = {}) { Text("Send requests")}
    }
}