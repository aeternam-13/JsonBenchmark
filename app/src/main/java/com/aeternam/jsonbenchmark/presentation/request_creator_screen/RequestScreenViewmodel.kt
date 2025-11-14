package com.aeternam.jsonbenchmark.presentation.request_creator_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aeternam.jsonbenchmark.model.RequestMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestScreenViewmodel @Inject constructor() : ViewModel() {
    private val _state =
        MutableStateFlow<RequestScreenState>(RequestScreenState.GatheringInfo(stateHolder = RequestScreenStateHolder()))
    val state: StateFlow<RequestScreenState> = _state

    private val _uiEvent = MutableSharedFlow<RequestScreenState>()
    val uiEvent: SharedFlow<RequestScreenState> = _uiEvent

    fun onIntent(intent: RequestScreenIntent) {
        val currentState = _state.value
        when (intent) {
            is RequestScreenIntent.RequestAmountChange -> {

                if (currentState is RequestScreenState.GatheringInfo) {
                    _state.value =
                        currentState.copy(stateHolder = currentState.stateHolder.copy(requestAmount = intent.amount))
                }
            }

            is RequestScreenIntent.ChangeRequestMode -> {
                if (currentState is RequestScreenState.GatheringInfo) {
                    _state.value =
                        currentState.copy(stateHolder = currentState.stateHolder.copy(requestMode = intent.requestMode))
                }
            }

            is RequestScreenIntent.SendRequests -> {
                sendRequest()
            }
        }
    }

    private fun sendRequest(){
        val currentState = _state.value
        if(currentState is RequestScreenState.GatheringInfo) {
            _state.value = RequestScreenState.Loading(currentState.stateHolder.copy())
            when (currentState.stateHolder.requestMode){
                RequestMode.OPTIMAL -> optimalPath()
                RequestMode.SLOWER -> slowerPath()
            }
        }
    }

    private fun optimalPath(){

        viewModelScope.launch {  }
    }

    private fun slowerPath(){

    }



}