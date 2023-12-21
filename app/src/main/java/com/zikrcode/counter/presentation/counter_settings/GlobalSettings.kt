package com.zikrcode.counter.presentation.counter_settings

import android.app.Activity
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ChangeScreenVisibility(keepScreenOn: Boolean) {
    val activity = LocalContext.current as Activity
    if (keepScreenOn) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    } else {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}