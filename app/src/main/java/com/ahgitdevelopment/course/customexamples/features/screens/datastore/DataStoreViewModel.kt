package com.ahgitdevelopment.course.customexamples.features.screens.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.course.customexamples.model.Phonebook
import com.ahgitdevelopment.course.customexamples.repository.DataStore_NAME
import com.ahgitdevelopment.course.customexamples.repository.ImplRepository
import com.ahgitdevelopment.course.customexamples.repository.datastore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.datastore : DataStore<Preferences> by  preferencesDataStore(name = DataStore_NAME)

class DataStoreViewModel(val app : Application) : AndroidViewModel(app) {

    companion object{
        val NAME = stringPreferencesKey("NAME")
        val PHONE_NUMBER = stringPreferencesKey("PHONE")
        val ADDRESS = stringPreferencesKey("ADDRESS")
    }

    var phoneState = MutableStateFlow("")
    var addressState = MutableStateFlow("")
    var nameState = MutableStateFlow("")

    var phonebook: MutableLiveData<Phonebook> = MutableLiveData()

    fun setPhone(phone: String) {
        phoneState.value = phone
    }

    fun setAddress(address: String) {
        addressState.value = address
    }

    fun setName(name: String) {
        nameState.value = name
    }

    fun saveData() {
        viewModelScope.launch(Dispatchers.IO) {
            app.datastore.edit { phonebooks->
                phonebooks[NAME] = phoneState.value
                phonebooks[PHONE_NUMBER]= addressState.value
                phonebooks[ADDRESS]= nameState.value
            }
        }
    }

    fun retrieveDate() {
        viewModelScope.launch(Dispatchers.IO) {
            app.datastore.data.map { phonebook ->
                Phonebook(
                    name = phonebook[NAME]!!,
                    address =  phonebook[ADDRESS]!!,
                    phone = phonebook[PHONE_NUMBER]!!
                )
            }

        }
    }
}