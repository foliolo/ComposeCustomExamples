package com.ahgitdevelopment.course.customexamples.repository.local.timer

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface TimerRepository {

    suspend fun saveTimer(key: Preferences.Key<Long>, timer: Long)

    fun getTimer(key: Preferences.Key<Long>): Flow<Long>
}