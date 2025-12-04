package com.aeternam.jsonbenchmark.presentation.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aeternam.jsonbenchmark.domain.model.RequestMode
import com.aeternam.jsonbenchmark.domain.model.Results
import java.util.Date
import java.util.Locale

@Composable
fun RequestScreenResults(results: Results, mode: RequestMode) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("$mode test results ", style = MaterialTheme.typography.headlineSmall)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            BoldDoubleText("Total: ", results.requestResult.size.toString())
            BoldDoubleText(
                "Total time(ms): ",
                results.requestResult.sumOf { it.totalTime }.toString()
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            BoldDoubleText("Success: ", results.success.toString())
            BoldDoubleText("Failures: ", results.failures.toString())
        }
        Spacer(Modifier.height(16.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 6.dp,
            border = BorderStroke(1.dp, Color.LightGray),
        ) {
            LazyColumn {
                items(results.requestResult) { result ->

                    ListItem(
                        headlineContent = {
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(result.detail)
                                Text("Time(ms): " + result.totalTime)
                            }
                        },
                        overlineContent = { Text(result.id.toString()) },
                        supportingContent = { Text("Sent at :" + result.timestamp.toFormattedDate()) },
                        trailingContent = {
                            Icon(
                                imageVector = if (result.success) Icons.Filled.DoneAll else Icons.Filled.ErrorOutline,
                                contentDescription = "Description item",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },

                        )
                }

            }
        }
    }
}

fun Long.toFormattedDate(pattern: String = "HH:mm:ss.SSS"): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.format(date)
}

@Composable
fun BoldDoubleText(boldText: String, text: String) {
    Row {
        Text(boldText, fontWeight = FontWeight.Bold)
        Text(text)
    }
}