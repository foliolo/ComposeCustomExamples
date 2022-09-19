package com.ahgitdevelopment.course.customexamples.model

import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.Calendar
import java.util.Date

class CustomCountDownTimer(
    val id: String,
    var startTime: Date = Calendar.getInstance().time,
    var endTime: Date = Calendar.getInstance().time,
    var remainTime: MutableState<Long> = mutableStateOf(
        endTime.time.minus(startTime.time)
    )

) {

//    fun getRemainTime(): Long =
//        endTime.time.minus(startTime.time).also {
//            Log.d("CustomCountDownTimer", it.toString())
//        }

    fun start(onTick: (Long) -> Unit, onFinish: () -> Unit) {
        object : CountDownTimer(remainTime.value, INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished)
            }

            override fun onFinish() {
                onFinish.invoke()
            }
        }
    }

    override fun toString(): String {
        return """
            id: \t\t $id
            startTime: \t ${startTime.time}
            endTime: \t ${startTime.time}
        """.trimIndent()
    }

    companion object {
        const val INTERVAL: Long = 1_000
        const val TIMER: Long = 15_000
    }
}

fun Long.toSeconds(): Long = this / 1000
