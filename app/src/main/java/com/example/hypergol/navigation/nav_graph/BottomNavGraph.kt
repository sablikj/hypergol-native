package com.example.hypergol.navigation.nav_graph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.navigation.BottomBarScreen
import com.example.hypergol.screens.launches.UpcomingLaunchesScreen
import com.example.hypergol.screens.launches.detail.LaunchDetail
import com.example.hypergol.screens.news.NewsScreen
import com.example.hypergol.screens.wiki.WikiScreen
import com.example.hypergol.util.Constants.Graph
import com.example.hypergol.util.Constants.Routes

@ExperimentalMaterialApi
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = BottomBarScreen.Launches.route
    ){
        // Main tab
        composable(route = BottomBarScreen.Launches.route){ backStackEntry ->
            UpcomingLaunchesScreen(
                onDetailClicked = { launchId ->
                    // To avoid duplicate navigation events
                    if(backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED){
                        navController.navigate("${Routes.LAUNCH_DETAIL_ROUTE}/$launchId")
                    }
                })
        }
        // News tab
        composable(route = BottomBarScreen.News.route){
            NewsScreen()
        }
        // Wiki tab
        composable(route = BottomBarScreen.Wiki.route){
            WikiScreen(navController)
        }
        // Launch detail
        composable(
            route = "${Routes.LAUNCH_DETAIL_ROUTE}/{${Routes.LAUNCH_DETAIL_ID}}",
            arguments = listOf(
                navArgument(Routes.LAUNCH_DETAIL_ID) {
                    type = NavType.StringType
                }
            ),
        ) {
            LaunchDetail()
        }
        wikiNavGraph(navController = navController)
    }
}