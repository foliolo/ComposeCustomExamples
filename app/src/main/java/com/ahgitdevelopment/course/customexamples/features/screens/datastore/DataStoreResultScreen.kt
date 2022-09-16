package com.ahgitdevelopment.course.customexamples.features.screens.datastore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import com.ahgitdevelopment.course.customexamples.ui.theme.CustomExamplesTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DataStoreResultScreen(
    navController: NavController,
    viewModel: DataStoreViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val phoneBook by viewModel.phoneBook.collectAsStateWithLifecycle(lifecycleOwner)
//    val phoneBook by viewModel.phoneBook.collectAsStateWithLifecycle(
//        initialValue = PhoneBook("", "", ""),
//        lifecycleOwner = lifecycleOwner
//    )

    DataStoreResultContent(phoneBook = phoneBook)
}

@Composable
fun DataStoreResultContent(phoneBook: PhoneBook) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Hello, ${phoneBook.name}!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "Your phone is ${phoneBook.phone}!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "Your address is ${phoneBook.address}!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h6
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewDataStoreResultContent() {
    CustomExamplesTheme {
        DataStoreResultContent(
            PhoneBook(
                name = "name",
                address = "somewhere street",
                phone = "123456"
            )
        )
    }
}
