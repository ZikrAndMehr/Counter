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

package com.zikrcode.counter.presentation.utils.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zikrcode.counter.presentation.utils.navigation.MainNavigationArgs.COUNTER_ID_ARG
import com.zikrcode.counter.presentation.add_edit_counter.AddEditCounterScreen
import com.zikrcode.counter.presentation.counter_home.CounterHomeScreen
import com.zikrcode.counter.presentation.counter_list.CounterListScreen
import com.zikrcode.counter.presentation.counter_settings.CounterSettingsScreen
import com.zikrcode.counter.presentation.utils.navigation.MainNavigationArgs.EDIT_COUNTER_ARG

@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CounterHomeScreen.route,
        modifier = modifier,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() }
    ) {
        composable(
            route = Screen.CounterHomeScreen.route + "?$COUNTER_ID_ARG={$COUNTER_ID_ARG}",
            arguments = listOf(
                navArgument(COUNTER_ID_ARG) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            CounterHomeScreen(
                onCounterEdit = { counterId ->
                    navController.navigate(
                        Screen.AddEditCounterScreen.route +
                                "?$EDIT_COUNTER_ARG=${true}&$COUNTER_ID_ARG=${counterId}"
                    )
                }
            )
        }
        composable(Screen.CounterListScreen.route) {
            CounterListScreen(
                onCounterSelect = { counterId ->
                    navController.navigate(
                        Screen.CounterHomeScreen.route + "?$COUNTER_ID_ARG=${counterId}"
                    ) {
                        popUpTo(Screen.CounterHomeScreen.route) {
                            inclusive = true
                        }
                    }
                },
                onCounterEdit = { counterId ->
                    navController.navigate(
                        Screen.AddEditCounterScreen.route +
                                "?$EDIT_COUNTER_ARG=${true}&$COUNTER_ID_ARG=${counterId}"
                    )
                },
                onAddCounter = {
                    navController.navigate(Screen.AddEditCounterScreen.route)
                }
            )
        }
        composable(Screen.CounterSettingsScreen.route) {
            CounterSettingsScreen()
        }
        composable(
            route = Screen.AddEditCounterScreen.route +
                    "?$EDIT_COUNTER_ARG={$EDIT_COUNTER_ARG}&$COUNTER_ID_ARG={$COUNTER_ID_ARG}",
            arguments = listOf(
                navArgument(EDIT_COUNTER_ARG) {
                    type = NavType.BoolType
                    defaultValue = false
                },
                navArgument(COUNTER_ID_ARG) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val editCounter = navBackStackEntry.arguments?.getBoolean(EDIT_COUNTER_ARG) ?: false
            AddEditCounterScreen(
                onBack = { navController.navigateUp() },
                editCounter = editCounter
            )
        }
    }
}