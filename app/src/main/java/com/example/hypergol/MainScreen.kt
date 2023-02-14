package com.example.hypergol

import androidx.compose.foundation.layout.*
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.navigation.BottomBarScreen
import com.example.hypergol.navigation.nav_graph.BottomNavGraph
import com.example.hypergol.screens.common.TopBar
import com.example.hypergol.util.Constants.Routes


@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun MainScreen(navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            when(currentRoute){
                "upcomingLaunches", "news", "wiki" -> {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                stringResource(id = R.string.app_name),
                                style = MaterialTheme.typography.headlineLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
                    )
                }
                Routes.WIKI_LAUNCHES_ROUTE -> {
                   TopBar(text = "Rocket Launches",
                       onSearchClicked = {
                           navController.navigate(Routes.LAUNCH_SEARCH_ROUTE)
                       },
                       navController = navController
                   )
                }
                Routes.WIKI_AGENCY_ROUTE -> {
                    TopBar(text = "Launch Providers",
                        onSearchClicked = {
                            navController.navigate(Routes.AGENCY_SEARCH_ROUTE)
                        },
                        navController = navController
                    )
                }
                Routes.WIKI_ROCKETS_ROUTE -> {
                    TopBar(text = "Rockets",
                        onSearchClicked = {
                            navController.navigate(Routes.ROCKET_SEARCH_ROUTE)
                        },
                        navController = navController
                    )
                }
                "searchLaunch", "searchRocket", "searchAgency" -> {}
                else -> {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text= "Detail",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        },
                        navigationIcon = { IconButton(
                            onClick = { navController.popBackStack() },
                            content = { Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )}
                        )},
                        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary)
                    )
                }
            }
        },
        bottomBar = { BottomBar(navController = navController) }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()) {
            BottomNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Launches,
        BottomBarScreen.News,
        BottomBarScreen.Wiki,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar() {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true, // If route from current dst matches route passed from screen, make it selected
        onClick = {
            navController.navigate(screen.route){
                // Back button always returns main page - Launches
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}