package com.zikrcode.counter.presentation.counter_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.zikrcode.counter.R
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun CounterItem(
    counter: Counter,
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit,
    onResetClick: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Dimens.SpacingDouble,
                    top = Dimens.SpacingDouble,
                    end = Dimens.SpacingDouble,
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = counter.counterName,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Dimens.SpacingSingle),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
            IconButton(
                onClick = onEditClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(Dimens.SpacingQuintuple)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = stringResource(R.string.edit)
                )
            }
            Spacer(Modifier.width(Dimens.SpacingSingle))
            IconButton(
                onClick = onResetClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(Dimens.SpacingQuintuple)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_reset),
                    contentDescription = stringResource(R.string.reset)
                )
            }
        }
        Text(
            text = counter.counterDescription,
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SpacingSingle),
            textAlign = TextAlign.Center,
            maxLines = 6,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun CounterItemPreview() {
    CounterItem(
        counter = Counter.instance(),
        onEditClick = { },
        onResetClick = { }
    )
}