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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens
import com.ahgitdevelopment.course.customexamples.ui.theme.CustomExamplesTheme

@Composable
fun DataStoreScreen(
    navController: NavController,
    viewModel: DataStoreViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val phoneBook by viewModel.phoneBook.collectAsStateWithLifecycle(lifecycleOwner)

    DataStoreContent(
        phoneBook = phoneBook,
        onSave = { viewModel.saveData(it) },
    ) {
        navController.navigate(AppScreens.DataStoreResultScreen.route)
    }
}

@Composable
fun DataStoreContent(
    phoneBook: PhoneBook,
    onSave: (PhoneBook) -> Unit,
    navigateEvent: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Hello, ${phoneBook.name.value}!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(value = phoneBook.name.value,
            onValueChange = { phoneBook.name.value = it },
            label = { Text(text = "Name...") })

        OutlinedTextField(value = phoneBook.phone.value,
            onValueChange = { phoneBook.phone.value = it },
            label = { Text(text = "Phone...") })

        OutlinedTextField(value = phoneBook.address.value,
            onValueChange = { phoneBook.address.value = it },
            label = { Text(text = "Address...") })

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { onSave(phoneBook) }) {
                Text(text = "Save Data")
            }
            Button(
                onClick = navigateEvent, colors = ButtonDefaults.buttonColors(
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
                        imageVector = Icons.Default.ArrowRight, contentDescription = "Right arrow"
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewDataStoreContent() {
    CustomExamplesTheme {
        DataStoreContent(
            phoneBook = PhoneBook(
                mutableStateOf("name"), mutableStateOf("address"), mutableStateOf("phone")
            ),
            onSave = {},
            navigateEvent = {}
        )
    }
}
