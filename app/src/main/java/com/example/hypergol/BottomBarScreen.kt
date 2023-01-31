package com.example.hypergol

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.hypergol.R


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
){
    object Launches : BottomBarScreen(
        route = "upcomingLaunches",
        title = "Launches",
        icon = R.drawable.ic_outline_rocket_launch_24
    )
    object News : BottomBarScreen(
        route = "news",
        title = "News",
        icon = R.drawable.ic_baseline_newspaper_24
    )
    object Wiki : BottomBarScreen(
        route = "wiki",
        title = "Wiki",
        icon = R.drawable.ic_outline_school_24
    )
}