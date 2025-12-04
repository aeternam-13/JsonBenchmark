package com.aeternam.jsonbenchmark.domain.model

data class Results(
    val success: Int = 0,
    val failures: Int = 0, val requestResult: List<RequestResult> = listOf()
)

data class RequestResult(val id: Int, val detail : String , val timestamp : Int, val success: Boolean,val totalTime : Int)