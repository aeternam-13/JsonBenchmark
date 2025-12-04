package com.aeternam.jsonbenchmark.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aeternam.jsonbenchmark.presentation.RequestScreenIntent
import com.aeternam.jsonbenchmark.presentation.RequestScreenState
import com.aeternam.jsonbenchmark.presentation.RequestScreenViewmodel
import com.aeternam.jsonbenchmark.presentation.rememberRequestScreenStateHolder
import com.aeternam.jsonbenchmark.presentation.screens.floatingbuttons.CustomFloatingButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen(
    viewmodel : RequestScreenViewmodel = hiltViewModel()
) {

    val state by viewmodel.state.collectAsStateWithLifecycle()
    val stateHolder = rememberRequestScreenStateHolder()

    LaunchedEffect(state) {
        when (state) {
            is RequestScreenState.GatheringInfo -> { stateHolder.clean() }
            is RequestScreenState.Loading -> {}
            is RequestScreenState.ShowResults -> {}
        }
    }

    Scaffold(
        floatingActionButtonPosition = if (state is RequestScreenState.ShowResults) FabPosition.Start else FabPosition.End,
        floatingActionButton = {
            when (state) {
                is RequestScreenState.GatheringInfo -> CustomFloatingButton(Icons.AutoMirrored.Default.Send) {
                    viewmodel.onIntent(
                        RequestScreenIntent.SendRequests(
                            stateHolder.requestAmount,
                            stateHolder.requestMode
                        )
                    )
                }

                is RequestScreenState.ShowResults -> CustomFloatingButton(Icons.AutoMirrored.Filled.ArrowBack) {
                    viewmodel.onIntent(RequestScreenIntent.BackToSend)
                }

                else -> null
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("JsonBenchMark", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },

        ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = 16.dp)


        ) {
            when (val current = state) {
                is RequestScreenState.GatheringInfo -> RequestScreenGatheringInfo(
                    stateHolder = stateHolder,
                    onRequestAmountChange = { newAmount -> stateHolder.onAmountChange(newAmount) },
                    onRequestModeChange = { mode -> stateHolder.onModeChange(mode) })
                is RequestScreenState.Loading -> RequestScreenLoading()
                is RequestScreenState.ShowResults -> RequestScreenResults(current.results , stateHolder.requestMode)
            }
        }
    }
}