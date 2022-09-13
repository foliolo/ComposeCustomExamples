package com.ahgitdevelopment.course.customexamples.repository.local.timer

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
    private val userPreferencesRepository: DataStore<Preferences>
) : TimerRepository {

    companion object {
        private val TIMER_KEY = longPreferencesKey("Timer")
    }

    override suspend fun saveTimer(timer: Long) {
        userPreferencesRepository.edit { preferences ->
            preferences[TIMER_KEY] = timer
        }
    }

    override fun getTimer(): Flow<Long> =
        userPreferencesRepository.data.map { preferences ->
            preferences[TIMER_KEY]?.toLong() ?: 0
        }
}