package com.ahgitdevelopment.course.customexamples.repository.local.timer

import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer
import kotlinx.coroutines.flow.Flow

interface TimerRepository {

    suspend fun saveTimer(customCountDownTimer: CustomCountDownTimer)

    fun getTimer(key: String): Flow<CustomCountDownTimer>
}