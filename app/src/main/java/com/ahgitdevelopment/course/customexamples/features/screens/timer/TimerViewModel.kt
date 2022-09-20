package com.ahgitdevelopment.course.customexamples.features.screens.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer
import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer.Companion.INTERVAL
import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer.Companion.TIMER
import com.ahgitdevelopment.course.customexamples.repository.local.timer.TimerRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerLocalRepository: TimerRepositoryImpl
) : ViewModel() {

    val timers: List<StateFlow<CustomCountDownTimer>> = TIMERS.map { customCountDownTimer ->
        timerLocalRepository.getTimer(customCountDownTimer.id)
            // I don't know why sometimes there are null values when it shouldn't
            .dropWhile {
                it == null || it.getRemainTime().value < INTERVAL
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = customCountDownTimer
            )
    }

    fun startTimer(customCountDownTimer: CustomCountDownTimer) = viewModelScope.launch {
        TIMERS.first { it.id == customCountDownTimer.id }.apply {
            Calendar.getInstance().time.let { currentDate ->
                CustomCountDownTimer(
                    id = id,
                    endTime = Date.from(currentDate.toInstant().plusMillis(TIMER))
                )
            }.let {
                timerLocalRepository.saveTimer(customCountDownTimer = it)
            }
        }
    }

    companion object {
        @JvmStatic
        private val TIMERS =
            listOf(
                CustomCountDownTimer(
                    "timer1",
                    Calendar.getInstance().time
                ),
                CustomCountDownTimer(
                    "timer2",
                    Calendar.getInstance().time
                ),
                CustomCountDownTimer(
                    "timer3",
                    Calendar.getInstance().time
                )
            )
    }
}


