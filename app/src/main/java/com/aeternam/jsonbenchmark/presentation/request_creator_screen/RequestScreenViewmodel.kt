package com.aeternam.jsonbenchmark.presentation.request_creator_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RequestScreenViewmodel @Inject constructor() : ViewModel() {
    private val _state =
        MutableStateFlow<RequestScreenState>(RequestScreenState.GatheringInfo(stateHolder = RequestScreenStateHolder()))
    val state: StateFlow<RequestScreenState> = _state

    fun onIntent(intent: RequestScreenIntent) {
        when (intent) {
            is RequestScreenIntent.RequestAmountChange -> {
                val currentState = _state.value
                if (currentState is RequestScreenState.GatheringInfo) {
                    _state.value =
                        currentState.copy(stateHolder = currentState.stateHolder.copy(requestAmount = intent.amount))
                }
            }
        }
    }

    private fun optimalPath(){

    }

    private fun worsePath(){

    }



}