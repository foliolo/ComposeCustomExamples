package com.ahgitdevelopment.course.customexamples.repository.local.timer

import kotlinx.coroutines.flow.Flow

interface TimerRepository {

    suspend fun saveTimer(timer: Long)

    fun getTimer(): Flow<Long>
}