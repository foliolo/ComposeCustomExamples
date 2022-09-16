package com.ahgitdevelopment.course.customexamples.features.screens.datastore.timer

import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.course.customexamples.repository.local.timer.TimerRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val SECOND: Long = 1_000L

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerLocalRepository: TimerRepositoryImpl
) : ViewModel() {

    private val currentTime = Calendar.getInstance().time.time

    private val _elapsedTime = MutableStateFlow(0L)
    val elapsedTime: StateFlow<Long> = _elapsedTime

//    val time: StateFlow<Long> = timerLocalRepository.getTimer()
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.Eagerly,
//            initialValue = TIMER
//        )


    init {
        viewModelScope.launch {
            timerLocalRepository.getTimer().collect { remainTime ->
                if (remainTime > currentTime) {
                    val cutOff = remainTime - currentTime
                    cutOff.initCountDownTimer()
                }
            }
        }
    }

    fun startTimer() {
        Log.i("Timer", "startTimer _elapsedTime.value ${_elapsedTime.value}")
        if (_elapsedTime.value < 1_000) {
            saveTimer(currentTime + TIMER)
            TIMER.initCountDownTimer()
        }
    }

    private fun saveTimer(tick: Long) {
        viewModelScope.launch {
            timerLocalRepository.saveTimer(tick)
        }
    }

    private fun Long.initCountDownTimer() {
        object : CountDownTimer(this, INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                _elapsedTime.value = millisUntilFinished
                Log.i("Timer", "onTick millisUntilFinished $millisUntilFinished")
            }

            override fun onFinish() {
                Log.i("Timer", "onFinish: ")
            }
        }.start()
    }

    companion object {
        private const val INTERVAL: Long = 1_000
        private const val TIMER: Long = 20_000
    }
}


