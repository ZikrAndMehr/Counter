package com.zikrcode.counter.presentation.counter_settings

sealed class CounterSettingsEvent {

    data class PreferenceChanged(val key: String) : CounterSettingsEvent()
}