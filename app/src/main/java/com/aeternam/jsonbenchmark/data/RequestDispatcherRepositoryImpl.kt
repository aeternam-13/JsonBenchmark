package com.aeternam.jsonbenchmark.data

import com.aeternam.jsonbenchmark.domain.model.RequestResult
import com.aeternam.jsonbenchmark.domain.model.Results
import com.aeternam.jsonbenchmark.domain.model.dummyclasses.DummyClass
import com.aeternam.jsonbenchmark.domain.repository.RequestDispatcherRepository
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import android.util.Base64
import java.lang.System
import  java.nio.charset.Charset

class RequestDispatcherRepositoryImpl(
    val api: RequestDispatcherApi,
    val gson: Gson = Gson()
) : RequestDispatcherRepository {
    override suspend fun optimalFlow(requestAmount: Int): Results = coroutineScope {
        val tasks: List<Deferred<RequestResult>> = (1..requestAmount).map { id ->
            async {
                val timestamp = System.currentTimeMillis()
                runCatching { api.optimalPost() }
                    .fold(
                        onSuccess = { response ->
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    val dummyClass = gson.fromJson(it, DummyClass::class.java)
                                    println(dummyClass.foo1)
                                }
                                handleSuccess(id, timestamp)
                            } else {
                                handleOnHttpError(id, response.code(), timestamp)
                            }
                        },
                        onFailure = { error ->
                            handleFailure(id, timestamp, error)
                        }
                    )
            }
        }

        getResults(tasks.awaitAll())
    }

    override suspend fun slowerFlow(requestAmount: Int): Results = coroutineScope {
        val tasks: List<Deferred<RequestResult>> = (1..requestAmount).map { id ->
            async {
                val timestamp = System.currentTimeMillis()
                runCatching { api.slowerPost() }
                    .fold(
                        onSuccess = { response ->
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    val dummyClass = gson.fromJson(it, DummyClass::class.java)
                                    println(dummyClass.foo1)

                                    val firstDecode =
                                        Base64.decode(dummyClass.target, Base64.DEFAULT)
                                    val secondDecode = Base64.decode(firstDecode, Base64.DEFAULT)

                                    dummyClass.target =
                                        secondDecode.toString(Charset.defaultCharset())
                                    println("Decoded")
                                    println(dummyClass.target)
                                }

                                handleSuccess(id, timestamp)
                            } else {
                                handleOnHttpError(id, response.code(), timestamp)
                            }
                        }, onFailure = { error -> handleFailure(id, timestamp, error) })
            }
        }
        getResults(tasks.awaitAll())
    }

    private fun getResults(allResults: List<RequestResult>): Results {
        return Results(
            success = allResults.count { it.success },
            failures = allResults.count { !it.success },
            requestResult = allResults
        )
    }

    private fun getTotalTime(start: Long): Long {
        val end = System.currentTimeMillis()
        return end - start
    }

    private fun handleOnHttpError(id: Int, errorCode: Int, timestamp: Long): RequestResult {
        return RequestResult(
            id = id,
            detail = "HTTP Error: $errorCode",
            timestamp = timestamp,
            totalTime = getTotalTime(timestamp),
            success = false
        )
    }

    private fun handleFailure(id: Int, timestamp: Long, error: Throwable): RequestResult {
        return RequestResult(
            id = id,
            detail = "Exception: ${error.localizedMessage}",
            timestamp = timestamp,
            totalTime = getTotalTime(timestamp),
            success = false
        )
    }

    private fun handleSuccess(id: Int, timestamp: Long): RequestResult {
        return RequestResult(
            id = id,
            //TODO may be good to add some extra stuff
            detail = "Success",
            timestamp = timestamp,
            totalTime = getTotalTime(timestamp),
            success = true
        )
    }

}