package com.aeternam.jsonbenchmark.data

import com.aeternam.jsonbenchmark.domain.model.Results
import com.aeternam.jsonbenchmark.domain.repository.RequestDispatcherRepository

class RequestDispatcherRepositoryImpl(
    val api : RequestDispatcherApi
) : RequestDispatcherRepository {
    override suspend fun optimalFlow(): Results {
        val response = api.optimalPost()

        val results = Results(0, 0, listOf())

        if (response.isSuccessful) {
            val result = response.body()

            result.let {

            }
        }

        return results
    }

    override suspend fun slowerFlow(): Results {
        TODO("Not yet implemented")
    }
}