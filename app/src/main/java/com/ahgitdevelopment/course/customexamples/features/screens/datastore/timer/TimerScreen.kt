package com.ahgitdevelopment.course.customexamples.features.screens.datastore.timer

import android.text.format.DateUtils
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val time by viewModel.elapsedTime.collectAsStateWithLifecycle(lifecycleOwner)

    CountDownText(
        elapsedTime = time,
        onStart = { viewModel.startTimer() }
    )
}

@Composable
private fun CountDownText(
    elapsedTime: Long,
    onStart: () -> Unit
) {
    val div = elapsedTime.div(1000)
    val second = DateUtils.formatElapsedTime(div)
    val color by animateColorAsState(
        targetValue = if (elapsedTime < 1_000) MaterialTheme.colors.primary else if (elapsedTime > 10_000) Color.Gray else Color.Red
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (elapsedTime < 1_000) "Start" else second,
            color = color,
            style = MaterialTheme.typography.h4.copy(
                fontWeight = FontWeight.W400
            ),
            modifier = Modifier
                .clickable {
                    onStart()
                }
        )
    }
}