package com.zikrcode.counter.presentation.counter_settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zikrcode.counter.domain.use_case.CounterUseCases
import com.zikrcode.counter.presentation.counter_settings.PreferencesKey.KEEP_SCREEN_ON_PREF_KEY
import com.zikrcode.counter.presentation.counter_settings.PreferencesKey.VIBRATE_PREF_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterSettingsViewModel @Inject constructor(
    private val counterUseCases: CounterUseCases
) : ViewModel() {

    private val _vibrateOnTap = mutableStateOf(false)
    val vibrateOnTap: State<Boolean> = _vibrateOnTap

    private val _keepScreenOn = mutableStateOf(false)
    val keepScreenOn: State<Boolean> = _keepScreenOn

    init {
        viewModelScope.apply {
            launch {
                counterUseCases.readUserPreferenceUseCase(
                    booleanPreferencesKey(VIBRATE_PREF_KEY)
                ).collectLatest {
                    _vibrateOnTap.value = it ?: false
                }
            }
            launch {
                counterUseCases.readUserPreferenceUseCase(
                    booleanPreferencesKey(KEEP_SCREEN_ON_PREF_KEY)
                ).collectLatest {
                    _keepScreenOn.value = it ?: false
                }
            }
        }
    }

    fun onEvent(counterSettingsEvent: CounterSettingsEvent) {
        when (counterSettingsEvent) {
            is CounterSettingsEvent.PreferenceChanged -> {
                viewModelScope.launch {
                    when (counterSettingsEvent.key) {
                        VIBRATE_PREF_KEY -> {
                            counterUseCases.writeUserPreferenceUseCase(
                                key = booleanPreferencesKey(VIBRATE_PREF_KEY),
                                value = !_vibrateOnTap.value
                            )
                        }
                        KEEP_SCREEN_ON_PREF_KEY -> {
                            counterUseCases.writeUserPreferenceUseCase(
                                key = booleanPreferencesKey(KEEP_SCREEN_ON_PREF_KEY),
                                value = !_keepScreenOn.value
                            )
                        }
                    }
                }
            }
        }
    }
}