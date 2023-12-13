package com.zikrcode.counter.presentation.counter_home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun DecrementButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SpacingSingle)
                .height(55.dp),
            shape = RoundedCornerShape(Dimens.CornerRadiusHalf)
        ) {
            Text(
                text = "-1",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun DecrementButtonPreview() {
    DecrementButton { }
}