package com.aeternam.jsonbenchmark.presentation.request_creator_screen

sealed class RequestScreenState {
    data class GatheringInfo(val stateHolder: RequestScreenStateHolder) : RequestScreenState()
    data object Loading : RequestScreenState()

}