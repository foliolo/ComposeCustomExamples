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
import javax.inject.Inject

private const val SECOND: Long = 1_000L

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val timerLocalRepository: TimerRepositoryImpl
) : ViewModel() {

    private lateinit var timer: CountDownTimer

    private val _elapsedTime = MutableStateFlow(0L)
    val elapsedTime: StateFlow<Long> = _elapsedTime

    fun startTimer() {
        viewModelScope.launch {
            if (_elapsedTime.value <= 0) {
                Log.i("Timer", "startTimer _elapsedTime.value ${_elapsedTime.value}")
                val selectedInterval = 15_000L
                val triggerTime = SystemClock.elapsedRealtime() + selectedInterval

                saveTime(triggerTime)
            }
        }
    }

    private fun saveTime(time: Long) {
        viewModelScope.launch {
            timerLocalRepository.saveTimer(time)
        }
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            timerLocalRepository.getTimer().catch { error ->
                Log.e(
                    this@TimerViewModel.javaClass.name,
                    "Error reading preferences.",
                    error
                )
            }.collect {
                Log.i(
                    "Timer",
                    "collect: timer = $it"
                )
                createTimer(it)
            }
        }
    }

    private fun createTimer(triggerTime: Long) {
        Log.i("Timer", "createTimer _elapsedTime.value ${_elapsedTime.value}")
        timer = object : CountDownTimer(triggerTime, SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _elapsedTime.value = triggerTime - SystemClock.elapsedRealtime()
                Log.i("Timer", "onTick _elapsedTime.value ${_elapsedTime.value}")
                if (_elapsedTime.value <= 0) {
                    resetTimer()
                }
            }

            override fun onFinish() {
                resetTimer()
            }
        }
        timer.start()
    }

    private fun resetTimer() {
        timer.cancel()
        _elapsedTime.value = 0
    }
}