package com.aeternam.jsonbenchmark.domain.model

data class Results(
    val success: Int,
    val failures : Int, val requestResult : List<RequestResult>)

data class RequestResult ( val id : Int , val detail : String , val timestamp : Int)