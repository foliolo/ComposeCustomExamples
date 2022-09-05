package com.ahgitdevelopment.course.customexamples.features.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahgitdevelopment.course.customexamples.ItemList
import com.ahgitdevelopment.course.customexamples.features.activities.ActivityA
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens

@Composable
fun AppList(navController: NavController) {
    val context = LocalContext.current
    Column {
        ItemList(
            "Splash Screen",
            onClick = {
                navController.navigate(AppScreens.SplashScreen.route)
            }
        )
        ItemList(
            "Navigate between Activities",
            onClick = {
                context.startActivity(Intent(context, ActivityA::class.java))
            }
        )
        ItemList(
            "Navigate between composables",
            onClick = {
                navController.navigate(AppScreens.ScreenA.route)
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewAppList() {
    val navController = rememberNavController()
    AppList(navController)
}
