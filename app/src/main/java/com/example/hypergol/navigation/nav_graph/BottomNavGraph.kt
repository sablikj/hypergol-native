package com.example.hypergol.navigation.nav_graph

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.BottomBarScreen
import com.example.hypergol.screens.launches.UpcomingLaunchesScreen
import com.example.hypergol.screens.launches.detail.LaunchDetail
import com.example.hypergol.screens.news.NewsScreen
import com.example.hypergol.screens.wiki.WikiScreen

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
                        navController.navigate("${LaunchRoute.LAUNCH_DETAIL_ROUTE}/$launchId")
                    }
                })
        }
        // News tab
        composable(route = BottomBarScreen.News.route){
            NewsScreen()
        }
        // Wiki tab
        composable(route = BottomBarScreen.Wiki.route){
            WikiScreen()
        }
        // Launch detail
        composable(
            route = "${LaunchRoute.LAUNCH_DETAIL_ROUTE}/{${LaunchRoute.LAUNCH_DETAIL_ID}}",
            arguments = listOf(
                navArgument(LaunchRoute.LAUNCH_DETAIL_ID) {
                    type = NavType.StringType
                }
            ),
        ) {
            LaunchDetail()
        }
    }
}
/*
fun NavGraphBuilder.detailNavGraph(navController: NavHostController){
    navigation(
        route = Graph.LAUNCH_DETAIL,
        startDestination = "upcomingLaunches/{id}"
    ){
        composable(route = "upcomingLaunches/{id}"){

        }
    }
}*/

object LaunchRoute {
    // Route for list of upcoming launches
    const val UPCOMING_LAUNCHES_ROUTE = "upcomingLaunches"
    // Route for single launch detail
    const val LAUNCH_DETAIL_ROUTE = "launch"
    // Route for launch Id
    const val LAUNCH_DETAIL_ID = "launchId"
}

object Graph {
    const val ROOT = "root_graph"
    const val LAUNCH_DETAIL = "details_graph"
}