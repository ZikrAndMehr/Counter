package com.zikrcode.counter.presentation.counter_settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zikrcode.counter.R
import com.zikrcode.counter.presentation.counter_settings.PreferencesKey.KEEP_SCREEN_ON_PREF_KEY
import com.zikrcode.counter.presentation.counter_settings.PreferencesKey.VIBRATE_PREF_KEY
import com.zikrcode.counter.presentation.counter_settings.components.PreferenceItem
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun CounterSettingsScreen(
    navController: NavController,
    viewModel: CounterSettingsViewModel = hiltViewModel()
) {
    CounterSettingsContent(
        vibrateOnTapChecked = viewModel.vibrateOnTap.value,
        keepScreenOn = viewModel.keepScreenOn.value,
        onEventClick = viewModel::onEvent
    )

    ChangeScreenVisibility(viewModel.keepScreenOn.value)
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun CounterSettingsContentPreview() {
    CounterSettingsContent(
        vibrateOnTapChecked = false,
        keepScreenOn = false,
        onEventClick = { }
    )
}

@Composable
private fun CounterSettingsContent(
    vibrateOnTapChecked: Boolean,
    keepScreenOn: Boolean,
    onEventClick: (CounterSettingsEvent) -> Unit
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.SpacingSingle)
        ) {
            PreferenceItem(
                icon = painterResource(R.drawable.ic_vibration),
                name = stringResource(R.string.vibrate_on_tap_name),
                description = stringResource(R.string.vibrate_on_tap_description),
                checked = vibrateOnTapChecked
            ) {
                onEventClick(CounterSettingsEvent.PreferenceChanged(VIBRATE_PREF_KEY))
            }
            Spacer(Modifier.height(Dimens.SpacingSingle))
            PreferenceItem(
                icon = painterResource(R.drawable.ic_brightness),
                name = stringResource(R.string.keep_screen_on_name),
                description = stringResource(R.string.keep_screen_on_description),
                checked = keepScreenOn
            ) {
                onEventClick(CounterSettingsEvent.PreferenceChanged(KEEP_SCREEN_ON_PREF_KEY))
            }
        }
    }
}