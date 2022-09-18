package com.ahgitdevelopment.course.customexamples.repository.local.phonebook

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhoneBookRepositoryImpl @Inject constructor(
    private val userPreferencesRepository: DataStore<Preferences>
) : PhoneBookRepository {

    override suspend fun savePhoneBook(phoneBook: PhoneBook) {
        userPreferencesRepository.edit { preferences ->
            preferences[NAME_KEY] = phoneBook.name.value
            preferences[PHONE_NUMBER_KEY] = phoneBook.phone.value
            preferences[ADDRESS_KEY] = phoneBook.address.value
        }
    }

    override fun getPhoneBook(): Flow<PhoneBook> =
        userPreferencesRepository.data.map { preferences ->
            PhoneBook(
                name = mutableStateOf(preferences[NAME_KEY] ?: ""),
                address = mutableStateOf(preferences[ADDRESS_KEY] ?: ""),
                phone = mutableStateOf(preferences[PHONE_NUMBER_KEY] ?: "")
            )
        }

    companion object {
        private val NAME_KEY = stringPreferencesKey("NAME")
        private val PHONE_NUMBER_KEY = stringPreferencesKey("PHONE")
        private val ADDRESS_KEY = stringPreferencesKey("ADDRESS")
    }
}