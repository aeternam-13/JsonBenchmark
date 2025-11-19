package com.aeternam.jsonbenchmark.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aeternam.jsonbenchmark.domain.model.RequestMode
import com.aeternam.jsonbenchmark.domain.repository.RequestDispatcherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestScreenViewmodel @Inject constructor(
    val repository: RequestDispatcherRepository
) : ViewModel() {
    private val _state =
        MutableStateFlow<RequestScreenState>(RequestScreenState.GatheringInfo(stateHolder = RequestScreenStateHolder()))
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RequestScreenState>()
    val uiEvent: SharedFlow<RequestScreenState> = _uiEvent

    fun onIntent(intent: RequestScreenIntent) {
        when (intent) {
            is RequestScreenIntent.SendRequests -> {
                sendRequest(intent.requestMode)
            }
        }
    }

    private fun sendRequest(requestMode: RequestMode) {
        _state.value = RequestScreenState.Loading
        when (requestMode) {
            RequestMode.OPTIMAL -> optimalPath()
            RequestMode.SLOWER -> slowerPath()
        }
    }

    private fun optimalPath(){
        viewModelScope.launch(Dispatchers.IO) {
            val results = repository.optimalFlow()

        }
    }

    private fun slowerPath(){

    }



}