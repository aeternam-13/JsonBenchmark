package com.aeternam.jsonbenchmark.domain.repository

import com.aeternam.jsonbenchmark.domain.model.Results

interface RequestDispatcherRepository {
    suspend fun optimalFlow(requestAmount : Int) : Results
    suspend fun slowerFlow(requestAmount : Int) : Results
}