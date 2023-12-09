package com.zikrcode.counter.presentation.counter_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zikrcode.counter.domain.model.Counter
import com.zikrcode.counter.presentation.counter_home.components.CircleButton
import com.zikrcode.counter.presentation.counter_home.components.CounterItem
import com.zikrcode.counter.presentation.counter_home.components.DecrementButton
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun CounterHomeScreen(
    navController: NavController,
    counterId: Int?
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.SpacingSingle),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CounterItem(
                counter = Counter.instance(),
                onEditClick = { },
                onResetClick = { }
            )
            Spacer(Modifier.height(Dimens.SpacingSingle))
            CircleButton(
                modifier = Modifier.weight(1f)
            ) {  }
            Spacer(Modifier.height(Dimens.SpacingSingle))
            DecrementButton {  }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun CounterHomeScreenPreview() {
    CounterHomeScreen(rememberNavController(), null)
}