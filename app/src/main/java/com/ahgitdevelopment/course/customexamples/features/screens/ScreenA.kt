package com.ahgitdevelopment.course.customexamples.features.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens
import com.ahgitdevelopment.course.customexamples.ui.theme.CustomExamplesTheme

@Composable
fun ScreenA(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(AppScreens.ScreenA.route) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = AppScreens.ScreenA.route)
                Button(
                    onClick = {
                        val param = "This is a param"
                        navController.navigate(AppScreens.ScreenB.route + "/${param}")
                    }
                ) {
                    Text(text = "Navigate to B")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewScreenA() {
    CustomExamplesTheme {
        val navController = rememberNavController()
        ScreenA(navController)
    }
}
