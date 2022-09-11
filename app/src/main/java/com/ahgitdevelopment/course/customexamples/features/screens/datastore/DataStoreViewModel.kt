package com.ahgitdevelopment.course.customexamples.features.screens.datastore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import com.ahgitdevelopment.course.customexamples.repository.local.PhoneBookLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val phoneBookLocalRepository: PhoneBookLocalRepository
) : ViewModel() {

    var phoneBook = flowOf(
        PhoneBook(
            name = "",
            address = "",
            phone = ""
        )
    )

    init {
        viewModelScope.launch {
            phoneBook = phoneBookLocalRepository.getPhoneBook().catch { error ->
                Log.e(
                    this@DataStoreViewModel.javaClass.name,
                    "Error reading preferences.",
                    error
                )
            }.map { phoneBook ->
                phoneBook
            }
        }
    }

//    fun setName(name: String) {
//        nameState.value = name
//        Log.d("VM setPhone", "${nameState.value} = $name")
//    }
//
//    fun setPhone(phone: String) {
//        phoneState.value = phone
//        Log.d("VM setPhone", "${phoneState.value} = $phone")
//    }
//
//    fun setAddress(address: String) {
//        addressState.value = address
//        Log.d("VM setPhone", "${addressState.value} = $address")
//    }

    fun saveData(phoneBook: PhoneBook) {
        viewModelScope.launch(Dispatchers.IO) {
            phoneBookLocalRepository.savePhoneBook(phoneBook)
        }
    }
}