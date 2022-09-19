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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer
import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer.Companion.INTERVAL

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TimerScreen(
    viewModel: TimerViewModel = hiltViewModel()
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val timers = viewModel.timers.map {
        it.collectAsStateWithLifecycle(lifecycleOwner)
    }.map {
        it.value
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(timers.dropWhile { it == null }) { customCountDownTimer ->
            Log.d("TimeScreen", customCountDownTimer.toString())
            CountDownText(customCountDownTimer, onClick = {
                viewModel.startTimer(it)
            })
        }
    }
}

@Composable
private fun CountDownText(
    customCountDownTimer: CustomCountDownTimer, onClick: (CustomCountDownTimer) -> Unit
) {

//    var remainTime by rememberSaveable {
//        customCountDownTimer.remainTime
//    }

    val color by animateColorAsState(
        targetValue = if (customCountDownTimer.remainTime.value < INTERVAL) MaterialTheme.colors.primary
        else if (customCountDownTimer.remainTime.value > 6_000) Color.DarkGray else Color.Red
    )

    customCountDownTimer.start(
        onTick = { customCountDownTimer.remainTime.value = it },
        onFinish = { customCountDownTimer.remainTime.value = 0L }
    )

    Text(text = if (customCountDownTimer.remainTime.value < INTERVAL) "Start" else customCountDownTimer.remainTime.toString(),
        color = color,
        style = MaterialTheme.typography.h4.copy(
            fontWeight = FontWeight.W600
        ),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                if (customCountDownTimer.remainTime.value < INTERVAL) onClick(customCountDownTimer)
            })


}


//@Composable
//private fun CountDownText(elapsedTime: Long) {
//    val div = elapsedTime.div(1000)
//    val second = DateUtils.formatElapsedTime(div)
//    val color by animateColorAsState(
//        targetValue = if (elapsedTime < 1_000) MaterialTheme.colors.primary
//        else if (elapsedTime > 6_000) Color.DarkGray else Color.Red
//    )
//    Text(text = if (elapsedTime < 1_000) "Start" else second,
//        color = color,
//        style = MaterialTheme.typography.h4.copy(
//            fontWeight = FontWeight.W600
//        ),
//        modifier = Modifier
//            .padding(8.dp)
//            .clickable {
//                onClick()
//            })
//}