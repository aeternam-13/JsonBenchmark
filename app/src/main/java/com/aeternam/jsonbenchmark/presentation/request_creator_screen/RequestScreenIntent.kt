package com.aeternam.jsonbenchmark.presentation.request_creator_screen

import com.aeternam.jsonbenchmark.model.RequestMode

sealed class RequestScreenIntent {
    data class RequestAmountChange(val amount: String) : RequestScreenIntent()
    data class ChangeRequestMode(val requestMode: RequestMode) : RequestScreenIntent()
    data object SendRequests : RequestScreenIntent()
}