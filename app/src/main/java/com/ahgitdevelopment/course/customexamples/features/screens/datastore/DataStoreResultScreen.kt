package com.ahgitdevelopment.course.customexamples.features.screens.datastore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ahgitdevelopment.course.customexamples.model.PhoneBook
import com.ahgitdevelopment.course.customexamples.ui.theme.CustomExamplesTheme

@Composable
fun DataStoreResultScreen(
    navController: NavController,
    viewModel: DataStoreViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val phoneBook by viewModel.phoneBook.collectAsStateWithLifecycle(lifecycleOwner)

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
            text = "Hello, ${phoneBook.name.value}!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "Your phone is ${phoneBook.phone.value}!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "Your address is ${phoneBook.address.value}!",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h6
        )
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun DefaultPreviewDataStoreResultContent() {
    CustomExamplesTheme {
        DataStoreResultContent(
            PhoneBook(
                name = remember{
                    mutableStateOf("name")
                },
                address = remember{
                    mutableStateOf("somewhere street")
                },
                phone =remember {
                    mutableStateOf("123456")
                }
            )
        )
    }
}
