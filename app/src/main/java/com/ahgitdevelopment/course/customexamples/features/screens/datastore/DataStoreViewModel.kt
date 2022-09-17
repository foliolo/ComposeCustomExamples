package com.ahgitdevelopment.course.customexamples.features.screens.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import com.ahgitdevelopment.course.customexamples.repository.local.phonebook.PhoneBookRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val phoneBookLocalRepository: PhoneBookRepositoryImpl
) : ViewModel() {

    val phoneBook: StateFlow<PhoneBook> = phoneBookLocalRepository.getPhoneBook()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PhoneBook("", "", "")
        )

    fun saveData(phoneBook: PhoneBook) {
        viewModelScope.launch {
            phoneBookLocalRepository.savePhoneBook(phoneBook)
        }
    }
}
