package com.ahgitdevelopment.course.customexamples.repository.local.timer

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ahgitdevelopment.course.customexamples.model.CustomCountDownTimer
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor(
    private val userPreferencesRepository: DataStore<Preferences>
) : TimerRepository {

    override suspend fun saveTimer(customCountDownTimer: CustomCountDownTimer) {
        userPreferencesRepository.edit { preferences ->
            preferences[stringPreferencesKey(customCountDownTimer.id)] =
                Gson().toJson(customCountDownTimer, CustomCountDownTimer::class.java)
        }
    }

    override fun getTimer(key: String): Flow<CustomCountDownTimer> =
        userPreferencesRepository.data.map { preferences ->
            preferences[stringPreferencesKey(key)].let { json ->
                Gson().fromJson(json, CustomCountDownTimer::class.java)
            }
        }
}