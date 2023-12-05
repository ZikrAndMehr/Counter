package com.zikrcode.counter.presentation.utils

sealed class Screen(val route: String) {

    object CounterHomeScreen: Screen("counter_home_screen")

    object CounterListScreen: Screen("counter_list_screen")

    object CounterSettingsScreen: Screen("counter_settings_screen")
}