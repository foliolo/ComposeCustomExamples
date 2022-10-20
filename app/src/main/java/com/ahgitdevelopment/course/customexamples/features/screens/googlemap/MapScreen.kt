package com.ahgitdevelopment.course.customexamples.features.screens.googlemap

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ahgitdevelopment.course.customexamples.features.components.TopAppBar
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

private val MADRID = LatLng(40.416729, -3.703339)

@Composable
fun MapScreen(
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = AppScreens.LazyListScreen.route,
                navController = navController
            )
        },
        content = { paddingVal ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingVal)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    MapContent()
                }
            }
        }
    )
}

@Composable
fun MapContent() {
    var isMapLoading by remember { mutableStateOf(true) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(MADRID, 15f)
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        onMapLoaded = { isMapLoading = false }) {
        Marker(
            position = cameraPositionState.position.target,
            title = cameraPositionState.position.target.toString()
        )
    }
    if (isMapLoading) {
        CircularProgressIndicator()
    }
}

