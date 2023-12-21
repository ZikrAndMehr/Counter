package com.zikrcode.counter.presentation.counter_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.zikrcode.counter.R
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.presentation.utils.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterGridItem(
    counter: Counter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        ElevatedCard(
            onClick = onClick,
            modifier = Modifier.padding(Dimens.SpacingSingle)
        ) {
            Text(
                text = counter.counterName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.SpacingSingle),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium
            )
            if (counter.counterDescription.isNotBlank()) {
                Divider(
                    modifier = Modifier.padding(horizontal = Dimens.SpacingSingle)
                )
                Text(
                    text = counter.counterDescription,
                    modifier = Modifier.padding(Dimens.SpacingSingle),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Divider(
                modifier = Modifier.padding(horizontal = Dimens.SpacingSingle)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = onDeleteClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = stringResource(R.string.delete),
                        tint = Color.Red
                    )
                }
                IconButton(
                    onClick = onEditClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = stringResource(R.string.edit)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CounterGridItemPreview() {
    CounterGridItem(
        counter = Counter.instance(),
        onClick = { },
        onDeleteClick = { },
        onEditClick = { },
    )
}