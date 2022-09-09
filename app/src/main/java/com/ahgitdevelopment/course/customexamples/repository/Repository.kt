package com.ahgitdevelopment.course.customexamples.repository

import com.ahgitdevelopment.course.customexamples.model.Phonebook
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun savePhoneBook(phonebook: Phonebook)

    suspend fun getPhoneBook():Flow<Phonebook>
}