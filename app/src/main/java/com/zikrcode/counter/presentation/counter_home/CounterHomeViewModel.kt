package com.zikrcode.counter.presentation.counter_home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.use_case.CounterUseCases
import com.zikrcode.counter.presentation.utils.AppConstants.COUNTER_VALUE_RANGE
import com.zikrcode.counter.presentation.utils.AppConstants.LAST_USED_COUNTER_ID_KEY
import com.zikrcode.counter.presentation.utils.navigation.MainNavigationArgs.COUNTER_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterHomeViewModel @Inject constructor(
    private val counterUseCases: CounterUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _counter = mutableStateOf<Counter?>(null)
    val counter: State<Counter?> = _counter

    private var saveCounterJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        loadCounter()
    }

    private fun loadCounter() {
        viewModelScope.launch {
            val counterId = savedStateHandle.get<Int>(COUNTER_ID_ARG)
                ?.takeIf { it != -1 }
                ?: counterUseCases.readUserPreferenceUseCase(
                    intPreferencesKey(LAST_USED_COUNTER_ID_KEY)
                )
            counterId?.let {
                _counter.value = counterUseCases.counterByIdUseCase(it)
            }
        }
    }

    fun onEvent(counterHomeEvent: CounterHomeEvent) {
        when(counterHomeEvent) {
            is CounterHomeEvent.Edit -> {
                _counter.value?.let {
                    viewModelScope.launch {
                        _eventFlow.emit(UiEvent.EditCounter(it))
                    }
                }
            }
            is CounterHomeEvent.Reset -> {
                _counter.value = counter.value?.copy(
                    counterSavedValue = 0
                )
                saveCounter()
            }
            is CounterHomeEvent.Increment -> {
                counter.value?.let { counter ->
                    if (counter.counterSavedValue + 1 in COUNTER_VALUE_RANGE) {
                        _counter.value = counter.copy(
                            counterSavedValue = counter.counterSavedValue.plus(1)
                        )
                        saveCounter()
                    }
                }
            }
            is CounterHomeEvent.Decrement -> {
                counter.value?.let { counter ->
                    if (counter.counterSavedValue - 1 in COUNTER_VALUE_RANGE) {
                        _counter.value = counter.copy(
                            counterSavedValue = counter.counterSavedValue.minus(1)
                        )
                        saveCounter()
                    }
                }
            }
            is CounterHomeEvent.Update -> {
                loadCounter()
            }
        }
    }

    private fun saveCounter() {
        saveCounterJob?.cancel()
        saveCounterJob = viewModelScope.launch {
            _counter.value?.let { counterUseCases.insertCounterUseCase(it) }
        }
    }

    sealed class UiEvent {

        object NoCounter : UiEvent()

        data class EditCounter(val counter: Counter) : UiEvent()
    }
}