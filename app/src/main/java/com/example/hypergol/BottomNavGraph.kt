package com.example.hypergol

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import com.example.hypergol.screens.news.NewsScreen
import com.example.hypergol.screens.launches.UpcomingLaunchesScreen
import com.example.hypergol.screens.WikiScreen

@OptIn(ExperimentalPagingApi::class)
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Launches.route
    ){
        composable(route = BottomBarScreen.Launches.route){
            UpcomingLaunchesScreen()
        }
        composable(route = BottomBarScreen.News.route){
            NewsScreen()
        }
        composable(route = BottomBarScreen.Wiki.route){
            WikiScreen()
        }
    }
}