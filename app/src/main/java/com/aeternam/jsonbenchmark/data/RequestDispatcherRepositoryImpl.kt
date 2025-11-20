package com.aeternam.jsonbenchmark.data

import com.aeternam.jsonbenchmark.domain.model.Results
import com.aeternam.jsonbenchmark.domain.repository.RequestDispatcherRepository

class RequestDispatcherRepositoryImpl(
    val api : RequestDispatcherApi
) : RequestDispatcherRepository {
    override suspend fun optimalFlow(): Results {
        val results = Results(0, 0, listOf())

        runCatching {
            val response = api.optimalPost()

            if (response.isSuccessful) {
                val result = response.body()

                result.let {

                }
            }else{
                println("something went wrong")
            }
        }.onFailure { error ->
            println("murio por " + error.toString())
         }


        return results
    }

    override suspend fun slowerFlow(): Results {
        TODO("Not yet implemented")
    }
}