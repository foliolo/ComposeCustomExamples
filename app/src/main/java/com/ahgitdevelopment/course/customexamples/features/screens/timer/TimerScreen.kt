package com.ahgitdevelopment.course.customexamples.features.screens.timer

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer
import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer.Companion.INTERVAL
import com.ahgitdevelopment.course.customexamples.model.toSeconds

@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel()
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val timers = viewModel.timers.map {
        it.collectAsStateWithLifecycle(lifecycleOwner).value
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(timers) { customCountDownTimer ->
            Log.d("TimeScreen", customCountDownTimer.toString())
            CountDownText(
                customCountDownTimer,
                onClick = {
                    viewModel.startTimer(it)
                }
            )
        }
    }
}

@Composable
private fun CountDownText(
    customCountDownTimer: CustomCountDownTimer,
    onClick: (CustomCountDownTimer) -> Unit
) {

    var remainTimeState by rememberSaveable {
        customCountDownTimer.getRemainTime()
    }

    val color by animateColorAsState(
        targetValue = if (remainTimeState < INTERVAL) MaterialTheme.colors.primary
        else if (remainTimeState > 6_000) Color.DarkGray else Color.Red
    )

    customCountDownTimer.start(
        onTick = { remainTimeState = it },
        onFinish = { remainTimeState = 0L }
    )

    Text(
        text = if (remainTimeState < INTERVAL)
            "Start"
        else
            remainTimeState.toSeconds().toString(),
        color = color,
        style = MaterialTheme.typography.h4.copy(
            fontWeight = FontWeight.W600
        ),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                if (remainTimeState < INTERVAL)
                    onClick(customCountDownTimer)
            }
    )
}
