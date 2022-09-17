package com.ahgitdevelopment.course.customexamples.model

import androidx.compose.runtime.saveable.mapSaver

data class PhoneBook(
    val name: String,
    val address: String,
    val phone: String
)

val PhoneBookSaver = run {
    val nameKey = "name"
    val addressKey = "address"
    val phoneKey = "phone"
    mapSaver(
        save = {
            mapOf(nameKey to it.name, addressKey to it.address, phoneKey to it.phone)
        },
        restore = {
            PhoneBook(
                name = it[nameKey] as String,
                address = it[addressKey] as String,
                phone = it[phoneKey] as String
            )
        }
    )
}
