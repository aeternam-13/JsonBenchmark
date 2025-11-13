package com.aeternam.jsonbenchmark.presentation.request_creator_screen

sealed class RequestScreenIntent {
    data class RequestAmountChange(val amount : String) : RequestScreenIntent()
}