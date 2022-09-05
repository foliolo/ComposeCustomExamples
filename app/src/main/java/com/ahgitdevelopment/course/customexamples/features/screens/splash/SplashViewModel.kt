package com.ahgitdevelopment.course.customexamples.features.screens.splash

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SplashViewModel : ViewModel() {

    private val _timer = MutableLiveData(SPLASH_TIMER)
    val timer: LiveData<Long> = _timer

    val finish = MutableStateFlow(false)

    init {
        object : CountDownTimer(SPLASH_TIMER, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timer.postValue(millisUntilFinished)
            }

            override fun onFinish() {
                finish.value = true
            }
        }.start()
    }

    companion object {
        const val SPLASH_TIMER: Long = 3_000
    }
}