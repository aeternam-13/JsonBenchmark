package com.aeternam.jsonbenchmark.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aeternam.jsonbenchmark.domain.model.RequestMode
import com.aeternam.jsonbenchmark.presentation.RequestScreenStateHolder

@Composable
fun RequestScreenGatheringInfo(
    stateHolder: RequestScreenStateHolder,
    onRequestAmountChange: (String) -> Unit,
    onRequestModeChange: (RequestMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            "Set the amount of request to the sent to the backend and chose which mode should be used to process the requests",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = stateHolder.requestAmount,
            label = { Text("Requests to perform") },
            onValueChange = { newValue ->
                if (newValue.isEmpty() || newValue.all { it.isDigit() }) {
                    onRequestAmountChange(newValue)
                }
            })
        Spacer(Modifier.height(16.dp))

        SingleChoiceSegmentedButtonRow {

            RequestMode.entries.toTypedArray().let {
                it.forEachIndexed { index, mode ->
                    SegmentedButton(
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = it.size
                        ),
                        onClick = { onRequestModeChange(it[index]) },
                        selected = mode == stateHolder.requestMode,
                        label = { Text(mode.toString()) }
                    )
                }
            }

        }
        //Button(onClick = {}) { Text("Send requests") }
    }
}