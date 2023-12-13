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
        composable(
            route = Screen.CounterHomeScreen.route + "?counterId={counterId}",
            arguments = listOf(
                navArgument(name = "counterId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val isCounterEdited = navBackStackEntry.savedStateHandle.get<Boolean>("isCounterEdited")
                ?: false
            CounterHomeScreen(
                navController = navController,
                isCounterEdited = isCounterEdited
            )
        }
        composable(Screen.CounterListScreen.route) {
            CounterListScreen(navController)
        }
        composable(Screen.CounterSettingsScreen.route) {
            CounterSettingsScreen(navController)
        }
        composable(
            route = Screen.AddEditCounterScreen.route + "?title={title}&counterId={counterId}",
            arguments = listOf(
                navArgument(name = "title") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(name = "counterId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { navBackStackEntry ->
            val title = navBackStackEntry.arguments?.getString("title")
            AddEditCounterScreen(
                navController = navController,
                title = title
            )
        }
    }
}