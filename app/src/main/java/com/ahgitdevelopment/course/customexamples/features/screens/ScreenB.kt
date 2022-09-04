package com.ahgitdevelopment.course.customexamples.features.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenB(navController: NavController, param: String?) {

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(AppScreens.ScreenB.route) },
            navigationIcon = {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
            }
        )
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Screen B")
            param?.let {
                Text(text = "Param: $it")
            }
            Button(onClick = {
                navController.popBackStack()
            }) {
                Text(text = "Navigate to A")
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewScreenB() {
    val navController = rememberNavController()
    ScreenB(navController, "Test")
}
