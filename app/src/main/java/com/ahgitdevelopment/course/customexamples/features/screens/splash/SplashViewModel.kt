package com.ahgitdevelopment.course.customexamples.features.screens.splash

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SplashViewModel : ViewModel() {

    val timer = MutableStateFlow(SPLASH_TIMER)
    val finish = MutableStateFlow(false)

    init {
        object : CountDownTimer(SPLASH_TIMER, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.value = millisUntilFinished
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