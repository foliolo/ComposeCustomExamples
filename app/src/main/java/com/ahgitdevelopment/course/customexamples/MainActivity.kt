package com.ahgitdevelopment.course.customexamples

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ahgitdevelopment.course.customexamples.features.activities.ActivityA
import com.ahgitdevelopment.course.customexamples.ui.theme.CustomExamplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomExamplesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppList()
                }
            }
        }
    }
}

@Composable
fun AppList() {
    val context = LocalContext.current
    Column {
        ItemList(
            "Navigate between Activities",
            onClick = { context.startActivity(Intent(context, ActivityA::class.java)) }
        )
        ItemList(
            "Navigate between Fragments",
            onClick = { /* TODO */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomExamplesTheme {
        AppList()
    }
}