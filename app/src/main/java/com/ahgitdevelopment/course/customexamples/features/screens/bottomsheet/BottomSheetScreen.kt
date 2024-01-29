package com.ahgitdevelopment.course.customexamples.features.screens.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahgitdevelopment.course.customexamples.ui.theme.CustomExamplesTheme
import com.skydoves.flexible.bottomsheet.material3.FlexibleBottomSheet
import com.skydoves.flexible.core.FlexibleSheetSize
import com.skydoves.flexible.core.rememberFlexibleBottomSheetState

@Composable
fun BottomSheetScreen(
) {
    var isShowingBottomSheet by rememberSaveable { mutableStateOf(false) }
    var isModal by rememberSaveable { mutableStateOf(false) }

    BottomSheetContent(
        isShowingBottomSheet,
        isModal,
        bottomClicked = {
            isShowingBottomSheet = true
        },
        toggleModal = {
            isModal = !isModal
        },
        onDismiss = {
            isShowingBottomSheet = false
        }
    )
}

@Composable
fun BottomSheetContent(
    isShowingBottomSheet: Boolean,
    isModal: Boolean,
    bottomClicked: () -> Unit,
    toggleModal: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (isShowingBottomSheet) {
        FlexibleBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = rememberFlexibleBottomSheetState(
                flexibleSheetSize = FlexibleSheetSize(
                    fullyExpanded = 0.9f,
                    intermediatelyExpanded = 0.5f,
                    slightlyExpanded = 0.15f,
                ),
                isModal = isModal,
                skipSlightlyExpanded = true,
            ),
            containerColor = Color.Black,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                OutlinedButton(onClick = toggleModal) {
                    Text(text = "Toggle Modal BottomSheet Status")
                }

                Spacer(modifier = Modifier.padding(top = 16.dp))

                Text(
                    text = "The BottomSheet is ${if (!isModal) "not" else ""} modal",
                    color = Color.White
                )
            }
        }
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(onClick = bottomClicked) {
            Text(text = "Show Bottom Sheet")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    CustomExamplesTheme {
        BottomSheetContent(
            isShowingBottomSheet = true,
            isModal = true,
            bottomClicked = {},
            toggleModal = {},
            onDismiss = {},
        )
    }
}
