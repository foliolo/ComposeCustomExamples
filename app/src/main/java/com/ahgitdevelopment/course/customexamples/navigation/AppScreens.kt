package com.ahgitdevelopment.course.customexamples.navigation

sealed class AppScreens(val route: String) {
    object AppList : AppScreens("AppList")
    object ScreenA : AppScreens("ScreenA")
    object ScreenB : AppScreens("ScreenB")
}
