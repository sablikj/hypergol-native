package com.example.hypergol.navigation.nav_graph

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.navigation.BottomBarScreen
import com.example.hypergol.screens.launches.detail.LaunchDetail
import com.example.hypergol.screens.wiki.astronauts.AstronautsScreen
import com.example.hypergol.screens.wiki.launches.LaunchesScreen
import com.example.hypergol.screens.wiki.agency.LspScreen
import com.example.hypergol.screens.wiki.agency.detail.AgencyDetailScreen
import com.example.hypergol.screens.wiki.rockets.RocketsScreen
import com.example.hypergol.util.Constants

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagingApi::class, ExperimentalCoilApi::class)
fun NavGraphBuilder.wikiNavGraph(navController: NavHostController) {
    navigation(
        route = Constants.Graph.WIKI,
        startDestination = BottomBarScreen.Wiki.route
    ) {
        // Launches
        composable(route = Constants.Routes.WIKI_LAUNCHES_ROUTE) { backStackEntry ->
            LaunchesScreen(
                onDetailClicked = { launchId ->
                    // To avoid duplicate navigation events
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Constants.Routes.LAUNCH_DETAIL_ROUTE}/$launchId")
                    }
                }
            )
        }
        // Rockets
        composable(route = Constants.Routes.WIKI_ROCKETS_ROUTE) { backStackEntry ->
            RocketsScreen(
                onDetailClicked = { rocketId ->
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Constants.Routes.ROCKET_DETAIL_ROUTE}/$rocketId")
                    }
                }
            )
        }
        // LSP / Agency
        composable(route = Constants.Routes.WIKI_LSP_ROUTE) { backStackEntry ->
            LspScreen(
                onAgencyDetail = { lspID ->
                    Log.d("wtf", lspID.toString())
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Constants.Routes.LSP_DETAIL_ROUTE}/$lspID")
                    }
                }
            )
        }
        // Astronauts
        composable(route = Constants.Routes.WIKI_ASTRONAUTS_ROUTE) { backStackEntry ->
            AstronautsScreen(
                onDetailClicked = { astronautId ->
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Constants.Routes.ASTRONAUT_DETAIL_ROUTE}/$astronautId")
                    }
                }
            )
        }
        // Details
        // Agency detail
        composable(
            route = "${Constants.Routes.LSP_DETAIL_ROUTE}/{${Constants.Routes.LSP_DETAIL_ID}}",
            arguments = listOf(
                navArgument(Constants.Routes.LSP_DETAIL_ID) {
                    type = NavType.IntType
                }
            ),
        ) {
            AgencyDetailScreen()
        }


    }
}