package com.ahgitdevelopment.course.customexamples.features.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ahgitdevelopment.course.customexamples.R
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = viewModel()) {

    val timer by viewModel.timer.collectAsStateWithLifecycle()
    val finish by viewModel.finish.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = finish) {
        if (finish) {
            navController.popBackStack()
            navController.navigate(AppScreens.AppList.route)
        }
    }

    Splash(timer)
}

@Composable
fun Splash(timer: Long) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Blue),
        )
        Text(
            text = "Splash Screen",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(12.dp)
        )
        Text(
            text = "Timer: ${timer / 1000} sec",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewSplashScreen() {
    Splash(2_000)
}
