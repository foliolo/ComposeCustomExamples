package com.ahgitdevelopment.course.customexamples.features.screens.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer
import com.ahgitdevelopment.course.customexamples.repository.local.timer.TimerRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerLocalRepository: TimerRepositoryImpl
) : ViewModel() {

    val timers: List<StateFlow<CustomCountDownTimer>> = TIMERS.map { customCountDownTimer ->
        timerLocalRepository.getTimer(customCountDownTimer.id)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = customCountDownTimer
            )
    }

//    private fun currentTime(): Long =
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//            android.icu.util.Calendar.getInstance().time.time
//        else Calendar.getInstance().time.time


    fun startTimer(customCountDownTimer: CustomCountDownTimer) = viewModelScope.launch {
        TIMERS.first { it.id == customCountDownTimer.id }.apply {
            startTime = Calendar.getInstance().time
            endTime = Calendar.getInstance().time.apply { time.plus(CustomCountDownTimer.TIMER) }
        }.let {
            timerLocalRepository.saveTimer(customCountDownTimer = it)
        }
    }

    companion object {
        private val TIMERS =
            listOf(
                CustomCountDownTimer("timer1"),
                CustomCountDownTimer("timer2")
            )
    }
}


