package com.ahgitdevelopment.course.customexamples.features.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens

@Composable
fun TopAppBar(navController: NavController, title: String){
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
        }
    )
}