package com.ahgitdevelopment.course.customexamples

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemList(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick }
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(16.dp),
            tint = Color.Black
        )
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
        Icon(
            imageVector = Icons.Filled.ArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(16.dp),
            tint = Color.Black
        )
    }
}
