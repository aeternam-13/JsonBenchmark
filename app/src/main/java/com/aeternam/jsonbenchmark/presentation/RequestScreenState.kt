package com.aeternam.jsonbenchmark.presentation

import com.aeternam.jsonbenchmark.domain.model.Results

sealed class RequestScreenState {
    data object GatheringInfo : RequestScreenState()
    data object Loading : RequestScreenState()

    data class ShowResults(val results: Results) : RequestScreenState()

}