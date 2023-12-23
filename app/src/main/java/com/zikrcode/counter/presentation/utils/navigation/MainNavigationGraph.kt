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
import com.zikrcode.counter.presentation.utils.navigation.MainNavigationArgs.TITLE_ARG
import com.zikrcode.counter.presentation.add_edit_counter.AddEditCounterScreen
import com.zikrcode.counter.presentation.counter_home.CounterHomeScreen
import com.zikrcode.counter.presentation.counter_list.CounterListScreen
import com.zikrcode.counter.presentation.counter_settings.CounterSettingsScreen

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
        composable(Screen.CounterHomeScreen.route) {
            CounterHomeScreen(navController)
        }
        composable(Screen.CounterListScreen.route) {
            CounterListScreen(navController)
        }
        composable(Screen.CounterSettingsScreen.route) {
            CounterSettingsScreen(navController)
        }
        composable(
            route = Screen.AddEditCounterScreen.route +
                    "?$TITLE_ARG={$TITLE_ARG}&$COUNTER_ID_ARG={$COUNTER_ID_ARG}",
            arguments = listOf(
                navArgument(TITLE_ARG) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(COUNTER_ID_ARG) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val title = navBackStackEntry.arguments?.getString(TITLE_ARG)
            AddEditCounterScreen(
                navController = navController,
                title = title
            )
        }
    }
}