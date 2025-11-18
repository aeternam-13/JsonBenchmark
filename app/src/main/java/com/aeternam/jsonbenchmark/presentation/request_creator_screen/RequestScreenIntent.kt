package com.aeternam.jsonbenchmark.presentation.request_creator_screen

import com.aeternam.jsonbenchmark.model.RequestMode

sealed class RequestScreenIntent {
    data class SendRequests(val amount: String, val requestMode: RequestMode) :
        RequestScreenIntent()
}