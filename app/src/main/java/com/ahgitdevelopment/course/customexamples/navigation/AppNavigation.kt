package com.ahgitdevelopment.course.customexamples.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahgitdevelopment.course.customexamples.features.screens.AppList
import com.ahgitdevelopment.course.customexamples.features.screens.ScreenA
import com.ahgitdevelopment.course.customexamples.features.screens.ScreenB
import com.ahgitdevelopment.course.customexamples.features.screens.gonbranch.ScreenGonA
import com.ahgitdevelopment.course.customexamples.features.screens.datastore.DataStoreResultScreen
import com.ahgitdevelopment.course.customexamples.features.screens.datastore.DataStoreScreen
import com.ahgitdevelopment.course.customexamples.features.screens.splash.SplashScreen
import com.ahgitdevelopment.course.customexamples.features.screens.LazyListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(route = AppScreens.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = AppScreens.AppList.route) {
            AppList(navController)
        }
        composable(route = AppScreens.ScreenA.route) {
            ScreenA(navController)
        }
        composable(
            route = AppScreens.ScreenB.route + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) {
            ScreenB(navController, it.arguments?.getString("text"))
        }
        composable(route = AppScreens.ExampleListScreen.route) {
            LazyListScreen(navController)
        }
        composable(route = AppScreens.DataStoreScreen.route) {
            DataStoreScreen(navController)
        }
        composable(route = AppScreens.DataStoreResultScreen.route) {
            DataStoreResultScreen(navController)
        }
    }
}
