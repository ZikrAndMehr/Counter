/*
 * Copyright (C) 2023 Zokirjon Mamadjonov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zikrcode.counter.presentation.counter_home

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zikrcode.counter.data.model.Counter
import com.zikrcode.counter.domain.use_case.CounterUseCases
import com.zikrcode.counter.presentation.counter_settings.PreferencesKey.KEEP_SCREEN_ON_PREF_KEY
import com.zikrcode.counter.presentation.counter_settings.PreferencesKey.VIBRATE_PREF_KEY
import com.zikrcode.counter.presentation.utils.AppConstants.COUNTER_VALUE_RANGE
import com.zikrcode.counter.presentation.utils.AppConstants.LAST_USED_COUNTER_ID_KEY
import com.zikrcode.counter.presentation.utils.navigation.MainNavigationArgs.COUNTER_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CounterHomeUiState(
    val isLoading: Boolean = false,
    val counter: Counter? = null,
    val vibrateOnTap: Boolean = false,
    val keepScreenOn: Boolean = false
)

@HiltViewModel
class CounterHomeViewModel @Inject constructor(
    private val counterUseCases: CounterUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CounterHomeUiState())
    val uiState = _uiState.asStateFlow()

    private var saveCounterJob: Job? = null

    init {
        loadCounter()
        loadPreferences()
    }

    private fun loadCounter() {
        _uiState.update {
            it.copy(isLoading = true)
        }

        val counterId = savedStateHandle.get<Int>(COUNTER_ID_ARG).takeIf { it != -1 }
        counterId?.let { id ->
            viewModelScope.launch {
                counterUseCases.writeUserPreferenceUseCase(
                    key = intPreferencesKey(LAST_USED_COUNTER_ID_KEY),
                    value = id
                )
                loadCounterById(id)
            }
        } ?: run {
            viewModelScope.launch {
                val lastUsedCounterId = counterUseCases.readUserPreferenceUseCase(
                    intPreferencesKey(LAST_USED_COUNTER_ID_KEY)
                ).first()
                lastUsedCounterId?.let {
                    loadCounterById(it)
                } ?: run {
                    _uiState.update {
                        it.copy(isLoading = false)
                    }
                }
            }
        }
    }

    private suspend fun loadCounterById(id: Int) {
        counterUseCases.counterByIdUseCase(id).collectLatest { counter ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    counter = counter
                )
            }
        }
    }

    private fun loadPreferences() {
        viewModelScope.apply {
            launch {
                counterUseCases.readUserPreferenceUseCase(
                    booleanPreferencesKey(VIBRATE_PREF_KEY)
                ).collectLatest { value ->
                    _uiState.update {
                        it.copy(vibrateOnTap = value ?: false)
                    }
                }
            }
            launch {
                val value = counterUseCases.readUserPreferenceUseCase(
                    booleanPreferencesKey(KEEP_SCREEN_ON_PREF_KEY)
                ).first() ?: false

                _uiState.update {
                    it.copy(keepScreenOn = value)
                }
            }
        }
    }

    fun onEvent(counterHomeEvent: CounterHomeEvent) {
        when(counterHomeEvent) {
            CounterHomeEvent.Reset -> {
                _uiState.update {
                    val counter = it.counter?.copy(counterSavedValue = 0)
                    it.copy(counter = counter)
                }
                saveCounter()
            }
            CounterHomeEvent.Increment -> {
                _uiState.update { state ->
                    val counter = state.counter?.let {
                        if (it.counterSavedValue + 1 in COUNTER_VALUE_RANGE) {
                            it.copy(counterSavedValue = it.counterSavedValue.plus(1))
                        } else {
                            it
                        }
                    }
                    state.copy(counter = counter)
                }
                saveCounter()
            }
            CounterHomeEvent.Decrement -> {
                _uiState.update { state ->
                    val counter = state.counter?.let {
                        if (it.counterSavedValue - 1 in COUNTER_VALUE_RANGE) {
                            it.copy(counterSavedValue = it.counterSavedValue.minus(1))
                        } else {
                            it
                        }
                    }
                    state.copy(counter = counter)
                }
                saveCounter()
            }
        }
    }

    private fun saveCounter() {
        saveCounterJob?.cancel()
        saveCounterJob = viewModelScope.launch {
            _uiState.value.counter?.let {
                counterUseCases.insertCounterUseCase(it)
            }
        }
    }
}