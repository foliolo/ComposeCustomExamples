package com.ahgitdevelopment.course.customexamples.model

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.Calendar
import java.util.Date

data class CustomCountDownTimer(
    val id: String,
//    val startTime: Date,
    val endTime: Date,
//    var remainTime: MutableState<Long> = mutableStateOf(0L)
) {

    fun start(onTick: (Long) -> Unit, onFinish: () -> Unit) {
        Log.d(
            "CountDownTimer",
            "CountDownTimer: ${endTime.time.minus(Calendar.getInstance().time.time)}"
        )
        object : CountDownTimer(endTime.time.minus(Calendar.getInstance().time.time), INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished)
            }

            override fun onFinish() {
                onFinish.invoke()
            }
        }.start()
    }


//    fun getRemainTime(): Long {
//        Log.d("dates", "end time: ${endTime.time}")
//        Log.d("dates", "start time: ${Calendar.getInstance().time.time}")
//        Log.d("dates", "remain time: ${endTime.time - Calendar.getInstance().time.time}")
//        return endTime.time - Calendar.getInstance().time.time
////        return Date.from(endTime.toInstant().minusMillis(startTime.time)).time
//    }

    fun getRemainTime(): MutableState<Long> {
        Log.d("dates", "end time: ${endTime.time}")
        Log.d("dates", "start time: ${Calendar.getInstance().time.time}")
        Log.d("dates", "remain time: ${endTime.time - Calendar.getInstance().time.time}")
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
        const val TIMER: Long = 60_000
    }
}

//fun Long.start(onTick: (Long) -> Unit, onFinish: () -> Unit) {
//    Log.d("CountDownTimer", "CountDownTimer: $this")
//    object : CountDownTimer(this, CustomCountDownTimer.INTERVAL) {
//        override fun onTick(millisUntilFinished: Long) {
//            onTick(millisUntilFinished)
//        }
//
//        override fun onFinish() {
//            onFinish.invoke()
//        }
//    }.start()
//}

fun Long.toSeconds(): Long = this / 1000
