package com.zikrcode.counter.presentation.counter_list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.presentation.utils.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterGridItem(
    counter: Counter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
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
            Divider()
            Text(
                text = counter.counterDescription,
                modifier = Modifier.padding(Dimens.SpacingSingle),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
fun CounterGridItemPreview() {
    CounterGridItem(
        counter = Counter.instance(),
        onClick = { }
    )
}