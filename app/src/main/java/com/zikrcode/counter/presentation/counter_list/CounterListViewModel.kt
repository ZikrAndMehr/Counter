package com.zikrcode.counter.presentation.counter_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zikrcode.counter.domain.use_case.CounterUseCases
import com.zikrcode.counter.domain.utils.CounterOrder
import com.zikrcode.counter.domain.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CounterListViewModel @Inject constructor(
    private val counterUseCases: CounterUseCases
) : ViewModel() {

    private var loadAllCountersJob: Job? = null
    private val _state = mutableStateOf(CounterListState())
    val state: State<CounterListState> = _state

    init {
        loadAllCounters(CounterOrder.Date(OrderType.DESCENDING))
    }

    private fun loadAllCounters(counterOrder: CounterOrder) {
        loadAllCountersJob?.cancel()
        loadAllCountersJob = counterUseCases.allCountersUseCase(counterOrder)
            .onEach { counters ->
                _state.value = state.value.copy(
                    allCounters = counters,
                    counterOrder = counterOrder
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(counterListEvent: CounterListEvent) {
        when (counterListEvent) {
            is CounterListEvent.Order -> {

            }
            is CounterListEvent.Delete -> {

            }
            is CounterListEvent.RestoreCounter -> {

            }
            is CounterListEvent.ToggleOrderSection -> {

            }
        }
    }
}