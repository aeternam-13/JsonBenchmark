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

class RequestDispatcherRepositoryImpl(
    val api : RequestDispatcherApi
) : RequestDispatcherRepository {
    override suspend fun optimalFlow(requestAmount: Int): Results = coroutineScope {
        val gson = Gson()
        val tasks: List<Deferred<RequestResult>> = (1..requestAmount).map { id ->
            async {
                val timestamp = (System.currentTimeMillis() / 1000).toInt()
                runCatching { api.optimalPost() }
                    .fold(
                    onSuccess = { response ->
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val dummyClass = gson.fromJson(it, DummyClass::class.java)
                                println(dummyClass.foo1)
                            }
                            RequestResult(
                                id = id,
                                detail = "Success: ${response.body()}",
                                timestamp = timestamp,
                                success = true
                            )
                        } else {
                            RequestResult(
                                id = id,
                                detail = "HTTP Error: ${response.code()}",
                                timestamp = timestamp,
                                success = false
                            )
                        }
                    },
                    onFailure = { error ->
                        RequestResult(
                            id = id,
                            detail = "Exception: ${error.localizedMessage}",
                            timestamp = timestamp,
                            success = false
                        )
                    }
                )
            }
        }

        val allResults = tasks.awaitAll()
        Results(
            success = allResults.count { it.success },
            failures = allResults.count { !it.success },
            requestResult = allResults
        )
    }

    override suspend fun slowerFlow(requestAmount: Int): Results {
        TODO("Not yet implemented")
    }

}