package com.example.hypergol.screens.news

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.hypergol.util.Constants.TWITTER_TIMELINE_DARK
import com.example.hypergol.util.Constants.TWITTER_TIMELINE_LIGHT


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsScreen(
    useDarkTheme: Boolean = isSystemInDarkTheme()
){
    val data = if(useDarkTheme){ TWITTER_TIMELINE_DARK } else {TWITTER_TIMELINE_LIGHT }

    Box(modifier = Modifier.fillMaxSize()){

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                WebView(it).apply {
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
