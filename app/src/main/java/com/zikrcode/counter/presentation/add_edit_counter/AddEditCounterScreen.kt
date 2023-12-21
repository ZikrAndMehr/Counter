package com.zikrcode.counter.presentation.add_edit_counter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zikrcode.counter.R
import com.zikrcode.counter.presentation.add_edit_counter.component.AddEditCounterForm
import com.zikrcode.counter.presentation.utils.Dimens
import com.zikrcode.counter.presentation.utils.navigation.MainNavigationArgs.UPDATE_COUNTER_ARG
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditCounterScreen(
    navController: NavController,
    title: String?,
    viewModel: AddEditCounterViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditCounterViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is AddEditCounterViewModel.UiEvent.CounterSaved,
                is  AddEditCounterViewModel.UiEvent.CounterCanceled -> {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        UPDATE_COUNTER_ARG, viewModel.isCounterEdited
                    )
                    navController.navigateUp()
                }
            }
        }
    }

    AddEditCounterContent(
        snackbarHostState = snackbarHostState,
        title = title,
        counterNameState = viewModel.counterName,
        counterValueState = viewModel.counterValue,
        counterDescriptionState = viewModel.counterDescription,
        onNavigateBackClick = navController::navigateUp,
        onEventClick = viewModel::onEvent
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun AddEditCounterContentPreview() {
    AddEditCounterContent(
        snackbarHostState = SnackbarHostState(),
        title = null,
        counterNameState = remember { mutableStateOf("") },
        counterDescriptionState = remember { mutableStateOf("") },
        counterValueState = remember { mutableStateOf("") },
        onNavigateBackClick = { },
        onEventClick = { }
    )
}

@Composable
private fun AddEditCounterContent(
    snackbarHostState: SnackbarHostState,
    title: String?,
    counterNameState: State<String>,
    counterValueState: State<String>,
    counterDescriptionState: State<String>,
    onNavigateBackClick: () -> Unit,
    onEventClick: (AddEditCounterEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.consumeWindowInsets(WindowInsets.systemBars),
        topBar = {
            AddEditCounterTopAppBar(
                onGoBackClick = onNavigateBackClick,
                title = title ?: stringResource(R.string.new_counter),
                onCancelClick = {
                    onEventClick(AddEditCounterEvent.Cancel)
                },
                onSaveClick = {
                    onEventClick(AddEditCounterEvent.Save)
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        contentWindowInsets = WindowInsets.systemBars
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(Dimens.SpacingSingle)
        ) {
            AddEditCounterForm(
                counterNameState = counterNameState,
                onCounterNameChange = { counterName ->
                    onEventClick(
                        AddEditCounterEvent.EnteredName(counterName)
                    )
                },
                counterDescriptionState = counterDescriptionState,
                onCounterDescriptionChange = { counterDescription ->
                    onEventClick(
                        AddEditCounterEvent.EnteredDescription(counterDescription)
                    )
                },
                counterValueState = counterValueState,
                onCounterValueChange = { counterValue ->
                    onEventClick(
                        AddEditCounterEvent.EnteredValue(counterValue)
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddEditCounterTopAppBar(
    onGoBackClick: () -> Unit,
    title: String,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = onGoBackClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.go_back)
                )
            }
        },
        actions = {
            IconButton(
                onClick = onCancelClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cancel),
                    contentDescription = stringResource(R.string.cancel)
                )
            }
            IconButton(
                onClick = onSaveClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_save),
                    contentDescription = stringResource(R.string.save)
                )
            }
        }
    )
}