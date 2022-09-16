package com.ahgitdevelopment.course.customexamples.repository.local.phonebook

import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import kotlinx.coroutines.flow.Flow

interface PhoneBookRepository {

    suspend fun savePhoneBook(phoneBook: PhoneBook)

    fun getPhoneBook(): Flow<PhoneBook>
}