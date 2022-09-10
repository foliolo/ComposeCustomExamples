package com.ahgitdevelopment.course.customexamples.features.screens.datastore

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.course.customexamples.model.Phonebook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "DataStore_NAME")

class DataStoreViewModel(private val app: Application) : AndroidViewModel(app) {

    companion object {
        val NAME = stringPreferencesKey("NAME")
        val PHONE_NUMBER = stringPreferencesKey("PHONE")
        val ADDRESS = stringPreferencesKey("ADDRESS")
    }

    var phoneState = MutableStateFlow("")
    var addressState = MutableStateFlow("")
    var nameState = MutableStateFlow("")

    var phonebook: MutableLiveData<Phonebook> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getData().collect {
                withContext(Dispatchers.Main) {
                    phonebook.value = it
                }
                Log.d(
                    "VM init", "Name ${it.name}" +
                            "Phone ${it.phone}" +
                            "Address ${it.address}"
                )
            }
        }
    }

    fun setName(name: String) {
        nameState.value = name
        Log.d("VM setPhone", "${nameState.value} = $name")
    }

    fun setPhone(phone: String) {
        phoneState.value = phone
        Log.d("VM setPhone", "${phoneState.value} = $phone")
    }

    fun setAddress(address: String) {
        addressState.value = address
        Log.d("VM setPhone", "${addressState.value} = $address")
    }

    fun saveData() {
        viewModelScope.launch(Dispatchers.IO) {
            app.datastore.edit { phonebooks ->
                phonebooks[NAME] = nameState.value
                phonebooks[PHONE_NUMBER] = phoneState.value
                phonebooks[ADDRESS] = addressState.value
            }
        }
    }

    private fun getData() = app.datastore.data.map { phonebook ->
        Phonebook(
            name = phonebook[NAME]!!,
            address = phonebook[ADDRESS]!!,
            phone = phonebook[PHONE_NUMBER]!!
        )
    }
}