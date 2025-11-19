package com.aeternam.jsonbenchmark.data

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Returned as JsonElement so it can be casted as needed after
 * */
interface RequestDispatcherApi {

    @GET("optimal")
    suspend fun optimalPost(): Response<JsonElement>

    @GET("slower")
    suspend fun slowerPost(): Response<JsonElement>
}