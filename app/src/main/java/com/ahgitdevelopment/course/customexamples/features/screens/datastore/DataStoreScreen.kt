package com.ahgitdevelopment.course.customexamples.features.screens.datastore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DataStoreScreen(navController: NavController, viewModel: DataStoreViewModel = viewModel()) {
    val context = LocalContext.current
    val phone by viewModel.phoneState.collectAsStateWithLifecycle()
    val address by viewModel.addressState.collectAsStateWithLifecycle()
    val name by viewModel.nameState.collectAsStateWithLifecycle()
    DataStoreContent(
        viewModel = viewModel,
        phone = phone,
        address = address,
        name = name,
        setPhone = { viewModel.setPhone(it) },
        setAddress = { viewModel.setAddress(it) },
        setName = { viewModel.setName(it) }
    )
}

@Composable
fun DataStoreContent(
    viewModel: DataStoreViewModel,
    phone: String,
    address: String,
    name: String,
    setPhone: (String) -> Unit,
    setAddress: (String) -> Unit,
    setName: (String) -> Unit
) {
    viewModel.retrieveDate()
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = phone,
            onValueChange = setPhone,
            label = { viewModel.phonebook.value?.let { Text(text = it.phone) } })
        OutlinedTextField(
            value = address,
            onValueChange = setAddress,
            label = { viewModel.phonebook.value?.let { Text(text = it.address) } })
        OutlinedTextField(
            value = name,
            onValueChange = setName,
            label = { viewModel.phonebook.value?.let { Text(text = it.name) } })
        Button(onClick = { viewModel.saveData() }) {
            Text(text = "Save Data")
        }
    }
}
