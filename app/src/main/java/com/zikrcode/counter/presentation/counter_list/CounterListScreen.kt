package com.zikrcode.counter.presentation.counter_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zikrcode.counter.R
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.presentation.counter_list.component.CounterGridItem
import com.zikrcode.counter.presentation.utils.Dimens
import com.zikrcode.counter.presentation.utils.navigation.Screen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CounterListScreen(
    navController: NavController,
    viewModel: CounterListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Scaffold(
        floatingActionButton = {
            CounterListScreenFAB(navController)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(it)
                .padding(Dimens.SpacingSingle)
        ) {
            CounterListStaggeredGrid(counters = state.allCounters)
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun CounterListScreenPreview() {
    CounterListScreen(rememberNavController())
}

@Composable
private fun CounterListScreenFAB(
    navController: NavController
) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Screen.AddEditCounterScreen.route)
        },
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = stringResource(R.string.add)
        )
    }
}

@Composable
private fun CounterListStaggeredGrid(counters: List<Counter>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2)
    ) {
        items(counters) { counter ->
            CounterGridItem(
                counter = counter
            ) {

            }
        }
    }
}