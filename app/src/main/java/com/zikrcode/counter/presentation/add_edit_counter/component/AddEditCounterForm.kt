package com.zikrcode.counter.presentation.add_edit_counter.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zikrcode.counter.R
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun AddEditCounterForm(
    modifier: Modifier = Modifier,
    counterNameState: State<String>,
    onCounterNameChange: (String) -> Unit,
    counterDescriptionState: State<String>,
    onCounterDescriptionChange: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = counterNameState.value,
            onValueChange = {
                 onCounterNameChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SpacingSingle),
            label = {
                Text(text = stringResource(R.string.counter_name))
            },
            maxLines = 1,
            minLines = 1
        )
        OutlinedTextField(
            value = counterDescriptionState.value,
            onValueChange = {
                onCounterDescriptionChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SpacingSingle),
            label = {
                Text(text = stringResource(R.string.counter_description))
            },
            maxLines = 6,
            minLines = 6
        )
    }
}

@Preview
@Composable
fun AddCounterFormPreview() {
    val state = remember { mutableStateOf("") }
    AddEditCounterForm(
        counterNameState = state,
        onCounterNameChange = { },
        counterDescriptionState = state,
        onCounterDescriptionChange = { }
    )
}