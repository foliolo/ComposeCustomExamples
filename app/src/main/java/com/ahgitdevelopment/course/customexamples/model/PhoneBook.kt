package com.ahgitdevelopment.course.customexamples.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class PhoneBook(
    val name: MutableState<String> = mutableStateOf(""),
    val address: MutableState<String> = mutableStateOf(""),
    val phone: MutableState<String> = mutableStateOf(""),
)
