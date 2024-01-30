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

package com.zikrcode.counter.presentation.counter_settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zikrcode.counter.R
import com.zikrcode.counter.presentation.counter_settings.PreferencesKey.KEEP_SCREEN_ON_PREF_KEY
import com.zikrcode.counter.presentation.counter_settings.PreferencesKey.VIBRATE_PREF_KEY
import com.zikrcode.counter.presentation.counter_settings.components.PreferenceItem
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun CounterSettingsScreen(
    viewModel: CounterSettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ChangeScreenVisibility(uiState.keepScreenOn)

    CounterSettingsContent(
        vibrateOnTapChecked = uiState.vibrateOnTap,
        keepScreenOn = uiState.keepScreenOn,
        onEvent = viewModel::onEvent
    )
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
        onEvent = { }
    )
}

@Composable
private fun CounterSettingsContent(
    vibrateOnTapChecked: Boolean,
    keepScreenOn: Boolean,
    onEvent: (CounterSettingsEvent) -> Unit
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
                onEvent(CounterSettingsEvent.PreferenceChanged(VIBRATE_PREF_KEY))
            }
            Spacer(Modifier.height(Dimens.SpacingSingle))
            PreferenceItem(
                icon = painterResource(R.drawable.ic_brightness),
                name = stringResource(R.string.keep_screen_on_name),
                description = stringResource(R.string.keep_screen_on_description),
                checked = keepScreenOn
            ) {
                onEvent(CounterSettingsEvent.PreferenceChanged(KEEP_SCREEN_ON_PREF_KEY))
            }
        }
    }
}