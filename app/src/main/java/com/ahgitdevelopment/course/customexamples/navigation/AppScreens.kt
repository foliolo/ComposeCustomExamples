package com.ahgitdevelopment.course.customexamples.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("SplashScreen")
    object AppList : AppScreens("AppList")
    object ScreenA : AppScreens("ScreenA")
    object ScreenB : AppScreens("ScreenB")
}
