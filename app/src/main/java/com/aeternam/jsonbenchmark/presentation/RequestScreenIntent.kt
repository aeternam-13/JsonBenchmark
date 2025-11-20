package com.aeternam.jsonbenchmark.presentation

import com.aeternam.jsonbenchmark.domain.model.RequestMode

sealed class RequestScreenIntent {
    data class SendRequests(val amount: String, val requestMode: RequestMode) :
        RequestScreenIntent()

    data object BackToSend : RequestScreenIntent()
}