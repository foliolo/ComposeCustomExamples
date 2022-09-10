package com.ahgitdevelopment.course.customexamples.features.screens.datastore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DataStoreScreen(navController: NavController, viewModel: DataStoreViewModel = viewModel()) {
    val phone by viewModel.phoneState.collectAsStateWithLifecycle()
    val address by viewModel.addressState.collectAsStateWithLifecycle()
    val name by viewModel.nameState.collectAsStateWithLifecycle()
    DataStoreContent(
        viewModel = viewModel,
        navController = navController,
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
    navController: NavController,
    phone: String,
    address: String,
    name: String,
    setPhone: (String) -> Unit,
    setAddress: (String) -> Unit,
    setName: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello, $name!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = name,
            onValueChange = setName,
            label = { Text(text = "Name...") }
        )
        OutlinedTextField(
            value = phone,
            onValueChange = setPhone,
            label = { Text(text = "Phone...") }
        )
        OutlinedTextField(
            value = address,
            onValueChange = setAddress,
            label = { Text(text = "Address...") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.saveData() }) {
                Text(text = "Save Data")
            }
            Button(
                onClick = { navController.navigate(AppScreens.DataStoreResult.route) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Next Screen"
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowRight,
                        contentDescription = "Flecha derecha"
                    )
                }
            }
        }
    }
}
