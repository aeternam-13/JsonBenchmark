package com.aeternam.jsonbenchmark.presentation

sealed class RequestScreenUiEvent {
    data class DisplayAlertDialog(val message: String) : RequestScreenUiEvent()
}