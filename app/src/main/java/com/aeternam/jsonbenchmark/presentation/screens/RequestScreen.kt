package com.aeternam.jsonbenchmark.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aeternam.jsonbenchmark.presentation.RequestScreenState
import com.aeternam.jsonbenchmark.presentation.RequestScreenViewmodel
import com.aeternam.jsonbenchmark.presentation.rememberRequestScreenStateHolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestScreen(
    viewmodel : RequestScreenViewmodel = hiltViewModel()
) {

    val state by viewmodel.state.collectAsStateWithLifecycle()
    val stateHolder = rememberRequestScreenStateHolder()

    LaunchedEffect(state) {

    }

    Scaffold(
        floatingActionButton = {
            if (state is RequestScreenState.GatheringInfo)
                FloatingActionButton(onClick = {}, containerColor = MaterialTheme.colorScheme.primary)
                {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Send,
                        contentDescription = "Save note"
                    )
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
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = 16.dp)

        ) {
            when (state) {
                is RequestScreenState.GatheringInfo -> RequestScreenGatheringInfo(
                    stateHolder = stateHolder,
                    onRequestAmountChange = { newAmount -> stateHolder.onAmount(newAmount) },
                    onRequestModeChange = { mode -> stateHolder.onModeChange(mode) })

                is RequestScreenState.Loading -> TODO()
            }
        }
    }
}