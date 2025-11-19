package com.aeternam.jsonbenchmark.presentation

sealed class RequestScreenState {
    data class GatheringInfo(val stateHolder: RequestScreenStateHolder) : RequestScreenState()
    data object Loading : RequestScreenState()

}