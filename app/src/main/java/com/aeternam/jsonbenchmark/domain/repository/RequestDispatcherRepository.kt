package com.aeternam.jsonbenchmark.domain.repository

import com.aeternam.jsonbenchmark.domain.model.Results

interface RequestDispatcherRepository {
    suspend fun optimalFlow() : Results
    suspend fun slowerFlow() : Results
}