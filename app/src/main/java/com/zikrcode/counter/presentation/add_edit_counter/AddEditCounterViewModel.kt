package com.zikrcode.counter.presentation.add_edit_counter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.use_case.CounterUseCases
import com.zikrcode.counter.presentation.utils.UiText
import com.zikrcode.counter.presentation.utils.navigation.MainNavigationArgs.COUNTER_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditCounterViewModel @Inject constructor(
    private val counterUseCases: CounterUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currentCounterId: Int? = null

    private val _counterName = mutableStateOf("")
    val counterName: State<String> = _counterName

    private val _counterDescription = mutableStateOf("")
    val counterDescription: State<String> = _counterDescription

    private val _counterValue = mutableStateOf("")
    val counterValue: State<String> = _counterValue

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var isCounterEdited: Boolean = false
        private set

    init {
        savedStateHandle.get<Int>(COUNTER_ID_ARG)?.let { counterId ->
            if (counterId != -1) {
                viewModelScope.launch {
                    counterUseCases.counterByIdUseCase(counterId)?.also { counter ->
                        currentCounterId = counter.id
                        _counterName.value = counter.counterName
                        _counterDescription.value = counter.counterDescription
                        _counterValue.value = counter.counterSavedValue.toString()
                    }
                }
            }
        }
    }

    fun onEvent(addEditCounterEvent: AddEditCounterEvent) {

        when (addEditCounterEvent) {
            is AddEditCounterEvent.EnteredName -> {
                _counterName.value = addEditCounterEvent.value
                isCounterEdited = true
            }
            is AddEditCounterEvent.EnteredValue -> {
                _counterValue.value = addEditCounterEvent.value
                isCounterEdited = true
            }
            is AddEditCounterEvent.EnteredDescription -> {
                _counterDescription.value = addEditCounterEvent.value
                isCounterEdited = true
            }
            AddEditCounterEvent.Cancel -> {
                _counterName.value = ""
                _counterDescription.value = ""
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.CounterCanceled)
                }
            }
            AddEditCounterEvent.Save -> {
                viewModelScope.launch {
                    val counterValidationResult = counterUseCases.insertCounterUseCase(
                        Counter(
                            id = currentCounterId,
                            counterName = counterName.value,
                            counterDescription = counterDescription.value,
                            counterDate = System.currentTimeMillis(),
                            counterSavedValue = counterValue.value.toIntOrNull() ?: 0
                        )
                    )

                    counterValidationResult.errorMessage?.let {
                        _eventFlow.emit(UiEvent.ShowSnackbar(it))
                    } ?: run {
                        _eventFlow.emit(UiEvent.CounterSaved)
                    }
                }
            }
        }
    }

    sealed class UiEvent {

        data class ShowSnackbar(val message: UiText) : UiEvent()

        object CounterCanceled : UiEvent()

        object CounterSaved : UiEvent()
    }
}