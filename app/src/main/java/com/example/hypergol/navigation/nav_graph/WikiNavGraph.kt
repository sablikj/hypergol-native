package com.example.hypergol.navigation.nav_graph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.navigation.BottomBarScreen
import com.example.hypergol.screens.wiki.astronauts.AstronautsScreen
import com.example.hypergol.screens.wiki.launches.LaunchesScreen
import com.example.hypergol.screens.wiki.lsp.LspScreen
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
                        navController.navigate("${Constants.Routes.ROCKET_DETAIL_ID}/$rocketId")
                    }
                }
            )
        }
        // LSP
        composable(route = Constants.Routes.WIKI_LSP_ROUTE) { backStackEntry ->
            LspScreen(
                onDetailClicked = { lspID ->
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Constants.Routes.LSP_DETAIL_ID}/$lspID")
                    }
                }
            )
        }
        // Astronauts
        composable(route = Constants.Routes.WIKI_ASTRONAUTS_ROUTE) { backStackEntry ->
            AstronautsScreen(
                onDetailClicked = { astronautId ->
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Constants.Routes.ASTRONAUT_DETAIL_ID}/$astronautId")
                    }
                }
            )
        }
    }
}