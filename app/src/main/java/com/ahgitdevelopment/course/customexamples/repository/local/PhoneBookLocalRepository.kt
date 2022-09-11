package com.ahgitdevelopment.course.customexamples.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhoneBookLocalRepository @Inject constructor(
    private val userPreferencesRepository: DataStore<Preferences>
) : LocalRepository {

    override suspend fun savePhoneBook(phoneBook: PhoneBook) {
        userPreferencesRepository.edit { preferences ->
            preferences[NAME_KEY] = phoneBook.name
            preferences[PHONE_NUMBER_KEY] = phoneBook.phone
            preferences[ADDRESS_KEY] = phoneBook.address
        }
    }

    override suspend fun getPhoneBook() = userPreferencesRepository.data.map { preferences ->
        PhoneBook(
            name = preferences[NAME_KEY] ?: "",
            address = preferences[ADDRESS_KEY] ?: "",
            phone = preferences[PHONE_NUMBER_KEY] ?: ""
        )
    }


    companion object {
        private val NAME_KEY = stringPreferencesKey("NAME")
        private val PHONE_NUMBER_KEY = stringPreferencesKey("PHONE")
        private val ADDRESS_KEY = stringPreferencesKey("ADDRESS")
    }
}