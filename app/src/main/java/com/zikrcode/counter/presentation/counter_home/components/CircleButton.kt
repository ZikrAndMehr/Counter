package com.zikrcode.counter.presentation.counter_home.components

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.zikrcode.counter.presentation.utils.AppConstants.VIBRATION_DURATION
import com.zikrcode.counter.presentation.utils.Dimens

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    currentValue: Int,
    vibrate: Boolean = false,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val vibrator = context.getSystemService(Vibrator::class.java)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .padding(Dimens.SpacingTriple)
                .clip(CircleShape)
                .border(Dimens.SpacingSingle, MaterialTheme.colorScheme.primary, CircleShape)
                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    if (vibrate) {
                        vibrator.vibrate(
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
                            } else {
                                VibrationEffect.createOneShot(
                                    VIBRATION_DURATION, VibrationEffect.DEFAULT_AMPLITUDE
                                )
                            }
                        )
                    }
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentValue.toString(),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge
            )
        }
    }
}

@Preview
@Composable
fun CircleButtonPreview() {
    CircleButton(
        currentValue = 0,
        onClick = { }
    )
}