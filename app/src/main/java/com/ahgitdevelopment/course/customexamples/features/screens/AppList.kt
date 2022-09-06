package com.ahgitdevelopment.course.customexamples.features.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahgitdevelopment.course.customexamples.features.activities.ActivityA
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens
import com.ahgitdevelopment.course.customexamples.ui.theme.CustomExamplesTheme

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
        ItemList(
            "Lazy List Screen",
            onClick = {
                navController.navigate(AppScreens.ExampleListScreen.route)
            }
        )
        ItemList(
            "Data Store Example ",
            onClick = {
                navController.navigate(AppScreens.DataStoreScreen.route)
            }
        )
    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewAppList() {
    CustomExamplesTheme {
        val navController = rememberNavController()
        AppList(navController)
    }
}
