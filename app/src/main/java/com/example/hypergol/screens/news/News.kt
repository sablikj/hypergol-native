package com.example.hypergol.screens.news

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Spinner
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.hypergol.util.Constants.TWITTER_TIMELINE_DARK
import com.example.hypergol.util.Constants.TWITTER_TIMELINE_LIGHT
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

//TODO: Rename screen files to *Screen

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsScreen(
    useDarkTheme: Boolean = isSystemInDarkTheme()
){
    val data = if(useDarkTheme){ TWITTER_TIMELINE_DARK } else {TWITTER_TIMELINE_LIGHT }

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

@Composable
fun Modal(loading: Boolean, function: () -> Unit) {

}
