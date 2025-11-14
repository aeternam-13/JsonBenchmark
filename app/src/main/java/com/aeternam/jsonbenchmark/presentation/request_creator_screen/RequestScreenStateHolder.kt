package com.aeternam.jsonbenchmark.presentation.request_creator_screen

import com.aeternam.jsonbenchmark.model.RequestMode

data class RequestScreenStateHolder (
    val requestAmount : String = "1",
    val requestMode: RequestMode = RequestMode.OPTIMAL
)