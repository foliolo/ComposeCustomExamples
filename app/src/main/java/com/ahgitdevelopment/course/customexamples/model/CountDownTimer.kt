package com.ahgitdevelopment.course.customexamples.model

import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.Calendar
import java.util.Date

data class CustomCountDownTimer(
    val id: String,
    val endTime: Date
) {

    fun start(onTick: (Long) -> Unit, onFinish: () -> Unit) {
        object : CountDownTimer(endTime.time.minus(Calendar.getInstance().time.time), INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished)
            }

            override fun onFinish() {
                onFinish()
            }
        }.start()
    }

    fun getRemainTime(): MutableState<Long> {
        return mutableStateOf(endTime.time - Calendar.getInstance().time.time)
    }

    override fun toString(): String {
        return """
            id: \t\t $id
            endTime: \t ${endTime.time}
        """.trimIndent()
    }

    companion object {
        const val INTERVAL: Long = 1_000
        const val TIMER: Long = 15_000
    }
}

fun Long.toSeconds(): Long = this / 1000
