package com.example.retobancopichincha.presentation.splash

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.retobancopichincha.R
import com.example.retobancopichincha.utils.Constanst
import com.example.retobancopichincha.utils.isFirstLaunch
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun SplashScreen(
    screenActionHandler: (SplashAction) ->Unit,
    events: SharedFlow<SplashEvent> = MutableSharedFlow(),
    navigation: (String) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        events.collect { event ->
            when (event) {
                is SplashEvent.Navigation -> navigation(event.navigation)
            }
        }
    }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        delay(Constanst.DELAY_TIME_MS)
        screenActionHandler(SplashAction.Navigation(context))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF21243A)),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { context ->
                ImageView(context).apply {
                    Glide.with(context)
                        .asGif()
                        .sizeMultiplier(.9f)
                        .load(R.drawable.splash_gif)
                        .into(this)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}