package com.ahgitdevelopment.course.customexamples.features.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ahgitdevelopment.course.customexamples.ItemList
import com.ahgitdevelopment.course.customexamples.features.activities.ActivityA
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens

@Composable
fun AppList(navController: NavController) {
    val context = LocalContext.current
    Column {
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
        ItemList(
            "Splash Screen",
            onClick = {
                navController.navigate(AppScreens.SplashScreen.route)
            }
        )
    }
}
