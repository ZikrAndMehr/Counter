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
import com.zikrcode.counter.presentation.utils.navigation.MainNavigationArgs.UPDATE_COUNTER_ARG
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
        composable(Screen.CounterHomeScreen.route) { navBackStackEntry ->
            val updateCounter = navBackStackEntry.savedStateHandle
                .get<Boolean>(UPDATE_COUNTER_ARG)
                ?: false
            CounterHomeScreen(
                navController = navController,
                updateCounter = updateCounter
            )
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