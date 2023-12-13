package com.zikrcode.counter.presentation.counter_home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.domain.use_case.CounterUseCases
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
        savedStateHandle.get<Int>("counterId")?.let { counterId ->
            if (counterId != -1) {
                viewModelScope.launch {
                    _counter.value = counterUseCases.counterByIdUseCase(counterId)
                }
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
                _counter.value = counter.value?.copy(
                    counterSavedValue = counter.value?.counterSavedValue?.plus(1) ?: 0
                )
                saveCounter()
            }
            is CounterHomeEvent.Decrement -> {
                _counter.value = counter.value?.copy(
                    counterSavedValue = counter.value?.counterSavedValue?.minus(1) ?: 0
                )
                saveCounter()
            }
            is CounterHomeEvent.CounterEdited -> {
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

        data class EditCounter(val counter: Counter) : UiEvent()
    }
}