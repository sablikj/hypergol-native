package com.example.hypergol.screens.news

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.hypergol.util.Constants.TWITTER_TIMELINE_DARK
import com.example.hypergol.util.Constants.TWITTER_TIMELINE_LIGHT


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsScreen(
    useDarkTheme: Boolean = isSystemInDarkTheme()
){
    val data = if(useDarkTheme){ TWITTER_TIMELINE_DARK } else {TWITTER_TIMELINE_LIGHT }
    val backgroundColor = MaterialTheme.colorScheme.background.toArgb()
    Box(modifier = Modifier.fillMaxSize()){

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                WebView(it).apply {
                    setBackgroundColor(backgroundColor)
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    setLayerType(View.LAYER_TYPE_HARDWARE, null)
                    settings.javaScriptEnabled = true
                    loadData(data, "text/html", null)
                }
            }, update = {
                it.loadData(data, "text/html", null)
            }
        )
    }
}
