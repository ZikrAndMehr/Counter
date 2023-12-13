package com.zikrcode.counter.presentation.counter_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zikrcode.counter.R
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.presentation.counter_home.components.CircleButton
import com.zikrcode.counter.presentation.counter_home.components.CounterItem
import com.zikrcode.counter.presentation.counter_home.components.DecrementButton
import com.zikrcode.counter.presentation.counter_home.components.NoCounterSelected
import com.zikrcode.counter.presentation.utils.Dimens
import com.zikrcode.counter.presentation.utils.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CounterHomeScreen(
    navController: NavController,
    isCounterEdited: Boolean,
    viewModel: CounterHomeViewModel = hiltViewModel()
) {
    val editCounterTitle = stringResource(R.string.edit_counter)

    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is CounterHomeViewModel.UiEvent.EditCounter -> {
                    navController.navigate(
                        Screen.AddEditCounterScreen.route +
                                "?title=${editCounterTitle}&counterId=${event.counter.id}"
                    )
                }
            }
        }
    }

    if (isCounterEdited) {
        viewModel.onEvent(CounterHomeEvent.CounterEdited)
    }

    CounterHomeContent(
        counter = viewModel.counter.value,
        onEventClick = viewModel::onEvent
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun CounterHomeContentPreview() {
    CounterHomeContent(
        counter = Counter.instance(),
        onEventClick = { }
    )
}

@Composable
private fun CounterHomeContent(
    counter: Counter?,
    onEventClick: (CounterHomeEvent) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.SpacingSingle),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (counter != null) {
                CounterItem(
                    counter = counter,
                    onEditClick = {
                        onEventClick(CounterHomeEvent.Edit(counter))
                    },
                    onResetClick = {
                        onEventClick(CounterHomeEvent.Reset)
                    }
                )
                Spacer(Modifier.height(Dimens.SpacingSingle))
                CircleButton(
                    modifier = Modifier.weight(1f),
                    currentValue = counter.counterSavedValue
                ) {
                    onEventClick(CounterHomeEvent.Increment)
                }
                Spacer(Modifier.height(Dimens.SpacingSingle))
                DecrementButton {
                    onEventClick(CounterHomeEvent.Decrement)
                }
            } else {
                NoCounterSelected()
            }
        }
    }
}