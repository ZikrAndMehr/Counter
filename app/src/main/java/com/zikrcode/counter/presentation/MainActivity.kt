package com.zikrcode.counter.presentation

import android.os.Build
import  android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zikrcode.counter.R
import com.zikrcode.counter.presentation.counter_home.CounterHomeScreen
import com.zikrcode.counter.presentation.counter_list.CounterListScreen
import com.zikrcode.counter.presentation.counter_settings.CounterSettingsScreen
import com.zikrcode.counter.presentation.utils.BottomNavigationItem
import com.zikrcode.counter.presentation.utils.Screen
import com.zikrcode.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CounterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConfigureSystemUi()
                    MainActivityContent()
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun GreetingPreview() {
    CounterTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainActivityContent()
        }
    }
}

@Composable
fun ComponentActivity.ConfigureSystemUi() {
    val darkTheme = isSystemInDarkTheme()
    val statusBarColor = MaterialTheme.colorScheme.background.toArgb()
    val navigationBarColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp).toArgb()

    DisposableEffect(darkTheme) {
        enableEdgeToEdge(
            statusBarStyle = when {
                Build.VERSION.SDK_INT >= 29 -> {
                    if (!darkTheme) {
                        SystemBarStyle.light(statusBarColor, statusBarColor)
                    } else {
                        SystemBarStyle.dark(statusBarColor)
                    }
                }
                else -> {
                    SystemBarStyle.auto(statusBarColor, statusBarColor) { darkTheme }
                }
            },
            navigationBarStyle = when {
                Build.VERSION.SDK_INT >= 29 -> {
                    if (!darkTheme) {
                        SystemBarStyle.light(navigationBarColor, navigationBarColor)
                    } else {
                        SystemBarStyle.dark(navigationBarColor)
                    }
                }
                else -> {
                    SystemBarStyle.auto(navigationBarColor, navigationBarColor) { darkTheme }
                }
            }
        )
        onDispose { }
    }
}

@Composable
fun MainActivityContent() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.CounterHomeScreen.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
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
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            route = Screen.CounterHomeScreen.route,
            title = stringResource(R.string.counter_home),
            selectedIcon = painterResource(R.drawable.ic_counter_filled),
            unselectedIcon = painterResource(R.drawable.ic_counter_outlined),
        ),
        BottomNavigationItem(
            route = Screen.CounterListScreen.route,
            title = stringResource(R.string.counter_list),
            selectedIcon = painterResource(R.drawable.ic_featured_filled),
            unselectedIcon = painterResource(R.drawable.ic_featured_outlined),
        ),
        BottomNavigationItem(
            route = Screen.CounterSettingsScreen.route,
            title = stringResource(R.string.counter_settings),
            selectedIcon = painterResource(R.drawable.ic_settings_filled),
            unselectedIcon = painterResource(R.drawable.ic_settings_outlined),
        )
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        bottomNavigationItems.forEach { bottomNavigationItem ->
            NavigationBarItem(
                selected = currentRoute == bottomNavigationItem.route,
                onClick = {
                    if (currentRoute != bottomNavigationItem.route) {
                        navController.navigate(bottomNavigationItem.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = if (currentRoute == bottomNavigationItem.route) {
                            bottomNavigationItem.selectedIcon
                        } else {
                            bottomNavigationItem.unselectedIcon
                        },
                        contentDescription = bottomNavigationItem.title
                    )
                },
                label = {
                    Text(text = bottomNavigationItem.title)
                }
            )
        }
    }
}