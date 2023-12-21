package com.zikrcode.counter.presentation.counter_settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.zikrcode.counter.R
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun PreferenceItem(
    modifier: Modifier = Modifier,
    icon: Painter,
    name: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.padding(
                start = Dimens.SpacingDouble,
                end = Dimens.SpacingSingle
            )
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(Dimens.SpacingSingle)
        ) {
            Text(
                text = name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(Dimens.SpacingHalf))
            Text(
                text = description,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.width(Dimens.SpacingSextuple)
        )
    }
}

@Preview
@Composable
fun PreferenceItemPreview() {
    PreferenceItem(
        icon = painterResource(R.drawable.ic_settings_outlined),
        name = stringResource(R.string.name),
        description = stringResource(R.string.description),
        checked = false,
        onCheckedChange = { }
    )
}