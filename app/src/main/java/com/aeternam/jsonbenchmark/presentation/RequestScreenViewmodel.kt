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
        MutableStateFlow<RequestScreenState>(RequestScreenState.GatheringInfo)
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RequestScreenState>()
    val uiEvent: SharedFlow<RequestScreenState> = _uiEvent

    fun onIntent(intent: RequestScreenIntent) {
        when (intent) {
            is RequestScreenIntent.SendRequests -> sendRequest(
                intent.requestMode,
                intent.amount.toInt()
            )

            is RequestScreenIntent.BackToSend -> _state.value = RequestScreenState.GatheringInfo
        }
    }

    private fun sendRequest(requestMode: RequestMode, requestAmount: Int) {
        _state.value = RequestScreenState.Loading
        when (requestMode) {
            RequestMode.OPTIMAL -> optimalPath(requestAmount)
            RequestMode.SLOWER -> slowerPath(requestAmount)
        }
    }

    private fun optimalPath(requestAmount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val results = repository.optimalFlow(requestAmount = requestAmount)
            _state.value = RequestScreenState.ShowResults(results = results)
        }
    }

    private fun slowerPath(requestAmount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val results = repository.slowerFlow(requestAmount = requestAmount)
            _state.value = RequestScreenState.ShowResults(results = results)
        }
    }


}