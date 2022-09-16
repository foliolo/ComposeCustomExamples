package com.ahgitdevelopment.course.customexamples.features.screens.datastore.timer

import android.text.format.DateUtils
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CountDownText(
            elapsedTime = viewModel.elapsedTimeA,
            onClick = { viewModel.onEvent(TimerEvent.OnTimerClickA) }
        )
        CountDownText(
            elapsedTime = viewModel.elapsedTimeB,
            onClick = { viewModel.onEvent(TimerEvent.OnTimerClickB) }
        )
    }
}

@Composable
private fun CountDownText(
    elapsedTime: Long,
    onClick: () -> Unit,
) {
    val div = elapsedTime.div(1000)
    val second = DateUtils.formatElapsedTime(div)
    val color by animateColorAsState(
        targetValue = if (elapsedTime < 1_000)
            MaterialTheme.colors.primary
        else if (elapsedTime > 6_000)
            Color.DarkGray else Color.Red
    )
    Text(
        text = if (elapsedTime < 1_000) "Start" else second,
        color = color,
        style = MaterialTheme.typography.h4.copy(
            fontWeight = FontWeight.W600
        ),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onClick()
            }
    )


}