package com.ahgitdevelopment.course.customexamples.features.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahgitdevelopment.course.customexamples.features.components.TopAppBar
import com.ahgitdevelopment.course.customexamples.navigation.AppScreens
import com.ahgitdevelopment.course.customexamples.ui.theme.CustomExamplesTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LazyListScreen(navController: NavController, names: List<String> = List(16) { "$it" }) {
    Scaffold(topBar = {
        TopAppBar(
            title = AppScreens.ExampleListScreen.route,
            navController = navController
        )
    }, content = {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
                Card(
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    CardContent(name)
                }
            }
        }
    })
}

@Composable
private fun CardContent(name: String, names: List<String> = List(101) { "$it" }) {

    var expanded by remember { mutableStateOf(false) }


    /*val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )*/

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                LazyRow(modifier = Modifier.padding(vertical = 13.dp)) {
                    items(items = names) { name ->
                        Text(
                            text = name,
                            style = MaterialTheme.typography.h6.copy(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Cursive
                            )

                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                }
                Text(
                    text = ("3 tristes tigres, " +
                            "comían trigo en un trigal. ").repeat(4),
                )

            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    "show less"
                    //stringResource(R.string.show_less)
                    //TODO: No funciona R.string?
                } else {
                    "show more"
                    //stringResource(R.string.show_more)
                }

            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    CustomExamplesTheme {
        //ScreenGonA()
    }
}