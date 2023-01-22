package com.example.hypergol

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Launches : BottomBarScreen(
        route = "upcomingLaunches",
        title = "Launches",
        icon = Icons.Default.Home
    )
    object News : BottomBarScreen(
        route = "news",
        title = "News",
        icon = Icons.Default.ThumbUp
    )
    object Wiki : BottomBarScreen(
        route = "wiki",
        title = "Wiki",
        icon = Icons.Default.AccountBox
    )
}