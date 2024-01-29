package com.ahgitdevelopment.course.customexamples.navigation

sealed class AppScreens(val route: String) {
    data object SplashScreen : AppScreens("SplashScreen")
    data object AppList : AppScreens("AppList")

    data object ScreenA : AppScreens("ScreenA")
    data object ScreenB : AppScreens("ScreenB")
    data object LazyListScreen : AppScreens("LazyListScreen")

    data object DataStoreScreen : AppScreens("DataStoreScreen")
    data object DataStoreResultScreen : AppScreens("DataStoreResultScreen")

    data object TimerScreen : AppScreens("TimerScreen")
    data object BottomSheet : AppScreens("BottomSheet")
}
