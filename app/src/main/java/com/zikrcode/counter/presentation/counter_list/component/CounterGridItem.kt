package com.zikrcode.counter.presentation.counter_list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun CounterGridItem(
    counter: Counter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .padding(Dimens.SpacingSingle)
                .clickable { onClick() },
        ) {
            Text(
                text = counter.counterName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.SpacingSingle),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
            Divider()
            Text(
                text = counter.counterDescription,
                modifier = Modifier.padding(Dimens.SpacingSingle),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun PreviewCounterGridItem() {
    CounterGridItem(
        counter = Counter.instance(),
        onClick = { }
    )
}