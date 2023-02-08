package com.example.hypergol.navigation.nav_graph

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
import com.example.hypergol.screens.wiki.agency.AgencySearchScreen
import com.example.hypergol.screens.wiki.astronauts.AstronautsScreen
import com.example.hypergol.screens.wiki.launches.LaunchesScreen
import com.example.hypergol.screens.wiki.agency.LspScreen
import com.example.hypergol.screens.wiki.agency.detail.AgencyDetailScreen
import com.example.hypergol.screens.wiki.launches.LaunchSearchScreen
import com.example.hypergol.screens.wiki.rockets.RocketSearchScreen
import com.example.hypergol.screens.wiki.rockets.RocketsScreen
import com.example.hypergol.screens.wiki.rockets.detail.RocketDetailScreen
import com.example.hypergol.util.Constants.Graph
import com.example.hypergol.util.Constants.Routes

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagingApi::class, ExperimentalCoilApi::class)
fun NavGraphBuilder.wikiNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.WIKI,
        startDestination = BottomBarScreen.Wiki.route
    ) {
        // Launches
        composable(route = Routes.WIKI_LAUNCHES_ROUTE) { backStackEntry ->
            LaunchesScreen(
                onDetailClicked = { launchId ->
                    // To avoid duplicate navigation events
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Routes.LAUNCH_DETAIL_ROUTE}/$launchId")
                    }
                }
            )
        }
        // Rockets
        composable(route = Routes.WIKI_ROCKETS_ROUTE) { backStackEntry ->
            RocketsScreen(
                onRocketDetail = { rocketId ->
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Routes.ROCKET_DETAIL_ROUTE}/$rocketId")
                    }
                }
            )
        }
        // LSP / Agency
        composable(route = Routes.WIKI_AGENCY_ROUTE) { backStackEntry ->
            LspScreen(
                onAgencyDetail = { lspID ->
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Routes.LSP_DETAIL_ROUTE}/$lspID")
                    }
                }
            )
        }
        // Astronauts
        composable(route = Routes.WIKI_ASTRONAUTS_ROUTE) { backStackEntry ->
            AstronautsScreen(
                onDetailClicked = { astronautId ->
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Routes.ASTRONAUT_DETAIL_ROUTE}/$astronautId")
                    }
                }
            )
        }
        // Details
        // Agency detail
        composable(
            route = "${Routes.LSP_DETAIL_ROUTE}/{${Routes.LSP_DETAIL_ID}}",
            arguments = listOf(
                navArgument(Routes.LSP_DETAIL_ID) {
                    type = NavType.IntType
                }
            ),
        ) {
            AgencyDetailScreen()
        }

        // Rocket detail
        composable(
            route = "${Routes.ROCKET_DETAIL_ROUTE}/{${Routes.ROCKET_DETAIL_ID}}",
            arguments = listOf(
                navArgument(Routes.ROCKET_DETAIL_ID) {
                    type = NavType.IntType
                }
            ),
        ) {
            RocketDetailScreen()
        }

        // Search
        // Launch search
        composable(route = Routes.LAUNCH_SEARCH_ROUTE){backStackEntry ->
            LaunchSearchScreen(
                navController = navController,
                onDetailClicked = { launchId ->
                    // To avoid duplicate navigation events
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Routes.LAUNCH_DETAIL_ROUTE}/$launchId")
                    }
                }
            )
        }

        // Agency search
        composable(route = Routes.AGENCY_SEARCH_ROUTE){backStackEntry ->
            AgencySearchScreen(
                navController = navController,
                onDetailClicked = { agencyId ->
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Routes.LSP_DETAIL_ROUTE}/$agencyId")
                    }
                }
            )
        }

        // Rocket search
        composable(route = Routes.ROCKET_SEARCH_ROUTE){backStackEntry ->
            RocketSearchScreen(
                navController = navController,
                onDetailClicked = { rocketId ->
                    if (backStackEntry.lifecycle.currentState == Lifecycle.State.RESUMED) {
                        navController.navigate("${Routes.ROCKET_DETAIL_ROUTE}/$rocketId")
                    }
                }
            )
        }

    }
}