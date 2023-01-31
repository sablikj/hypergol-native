package com.example.hypergol.screens.news

import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.hypergol.util.Constants.TWITTER_TIMELINE


@Composable
fun NewsScreen(){
    TwitterTimeline()
}

@Composable
fun TwitterTimeline(){
        AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            settings.javaScriptEnabled = true
            loadData(TWITTER_TIMELINE, "text/html", "UTF-8")
        }
    })
}

@Composable
@Preview
fun NewsScreenPreview(){
    NewsScreen()
}