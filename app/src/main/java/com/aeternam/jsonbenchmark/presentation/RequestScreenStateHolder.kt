package com.aeternam.jsonbenchmark.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.aeternam.jsonbenchmark.domain.model.RequestMode


@Composable
fun rememberRequestScreenStateHolder() : RequestScreenStateHolder{
    return remember { RequestScreenStateHolder() }
}

@Stable
class RequestScreenStateHolder {
    var requestAmount: String by mutableStateOf("0")
        private set

    var requestMode: RequestMode by mutableStateOf(RequestMode.OPTIMAL)
        private set

    fun onAmount(newAmount: String) {
        requestAmount = newAmount
    }

    fun onModeChange(newMode: RequestMode) {
        requestMode = newMode
    }

}