package com.ahgitdevelopment.course.customexamples.features.screens.datastore

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import com.ahgitdevelopment.course.customexamples.repository.local.phonebook.PhoneBookRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val phoneBookLocalRepository: PhoneBookRepositoryImpl
) : ViewModel() {

    private val _phoneBook = MutableStateFlow(PhoneBook(name = "", address = "", phone = ""))
    val phoneBook: StateFlow<PhoneBook> = _phoneBook

//    val phoneBook: StateFlow<PhoneBook> = phoneBookLocalRepository.getPhoneBook()
//        .dropWhile { it.isEmpty() }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.Lazily,
//            initialValue = PhoneBook("", "", "")
//        )

    init {
        viewModelScope.launch {
            phoneBookLocalRepository.getPhoneBook().catch { error ->
                Log.e(
                    this@DataStoreViewModel.javaClass.name, "Error reading preferences.", error
                )
            }.collect {
                _phoneBook.value = it
            }
        }
    }

    fun saveData(phoneBook: PhoneBook) {
        viewModelScope.launch {
            phoneBookLocalRepository.savePhoneBook(phoneBook)
        }
    }
}
