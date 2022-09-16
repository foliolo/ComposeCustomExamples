package com.ahgitdevelopment.course.customexamples.repository.local.timer

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
    private val userPreferencesRepository: DataStore<Preferences>
) : TimerRepository {

    override suspend fun saveTimer(key: Preferences.Key<Long>, timer: Long) {
        userPreferencesRepository.edit { preferences ->
            preferences[key] = timer
        }
    }

    override fun getTimer(key: Preferences.Key<Long>): Flow<Long> =
        userPreferencesRepository.data.map { preferences ->
            preferences[key]?.toLong() ?: 0
        }
}