package com.zikrcode.counter.presentation.counter_home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.zikrcode.counter.R
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun NoCounterSelected() {
    OutlinedCard(
        modifier = Modifier.padding(Dimens.SpacingSingle)
    ) {
        Text(
            modifier = Modifier.padding(Dimens.SpacingDouble),
            text = stringResource(R.string.no_counter_selected),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun NoCounterSelectedPreview() {
    NoCounterSelected()
}