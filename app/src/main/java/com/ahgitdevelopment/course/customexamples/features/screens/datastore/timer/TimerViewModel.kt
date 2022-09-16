package com.ahgitdevelopment.course.customexamples.features.screens.datastore.timer

import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.course.customexamples.repository.local.timer.TimerRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerLocalRepository: TimerRepositoryImpl
) : ViewModel() {

    var elapsedTimeA by mutableStateOf(0L)
    var elapsedTimeB by mutableStateOf(0L)

    init {
        viewModelScope.launch {
            launch {
                getTimer(key = TIMER_A_KEY)
            }
            launch {
                getTimer(key = TIMER_B_KEY)
            }
        }
    }

    private suspend fun getTimer(key: Preferences.Key<Long>) {
        timerLocalRepository.getTimer(key = key).collect { remainTime ->
            Log.d("Timer", "getTimer: $key key")
            if (remainTime > currentTime()) {
                (remainTime - currentTime()).initCountDownTimer(key = key)
            }
        }
    }

    fun onEvent(event: TimerEvent) {
        when (event) {
            is TimerEvent.OnTimerClickA -> {
                if (elapsedTimeA < 1_000) {
                    startAndSaveTimer(key = TIMER_A_KEY)
                }
            }
            is TimerEvent.OnTimerClickB -> {
                if (elapsedTimeB < 1_000) {
                    startAndSaveTimer(key = TIMER_B_KEY)
                }
            }
        }
    }

    private fun startAndSaveTimer(key: Preferences.Key<Long>) {
        Log.d("Timer", "startAndSaveTimer: $key key")
        viewModelScope.launch {
            timerLocalRepository.saveTimer(key = key, timer = currentTime() + TIMER)
        }
    }

    private fun currentTime(): Long =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            android.icu.util.Calendar.getInstance().time.time
        else Calendar.getInstance().time.time

    private fun Long.initCountDownTimer(key: Preferences.Key<Long>) {
        object : CountDownTimer(this, INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("Timer", "onTick: $key key // $millisUntilFinished ")
                if (key == TIMER_A_KEY)
                    elapsedTimeA = millisUntilFinished
                else
                    elapsedTimeB = millisUntilFinished
            }

            override fun onFinish() {
                Log.i("Timer", "onFinish: ")
            }
        }.start()
    }

    companion object {
        private const val INTERVAL: Long = 1_000
        private const val TIMER: Long = 15_000

        private val TIMER_A_KEY = longPreferencesKey("TimerA")
        private val TIMER_B_KEY = longPreferencesKey("TimerB")
    }
}


