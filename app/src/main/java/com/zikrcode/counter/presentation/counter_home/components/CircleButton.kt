package com.zikrcode.counter.presentation.counter_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    currentValue: Int = 0,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .padding(Dimens.SpacingTriple)
                .clip(CircleShape)
                .border(Dimens.SpacingSingle, MaterialTheme.colorScheme.primary, CircleShape)
                .background(MaterialTheme.colorScheme.background)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentValue.toString(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge
            )
        }
    }
}

@Preview
@Composable
fun CircleButtonPreview() {
    CircleButton {  }
}