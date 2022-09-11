package com.ahgitdevelopment.course.customexamples.repository.local

import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun savePhoneBook(phonebook: PhoneBook)

    suspend fun getPhoneBook(): Flow<PhoneBook>
}