package com.example.hypergol

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hypergol.screens.NewsScreen

@Composable
fun MainScreen() {
    /*
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController)}
    ){
        // Contains destinations for the bottom navbar
        BottomNavGraph(navController = navController)
    }*/
    val navController = rememberNavController()

    val screens = listOf(
        BottomBarScreen.Launches,
        BottomBarScreen.News,
        BottomBarScreen.Wiki,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.Primary))
            )
        },
        bottomBar = {
            NavigationBar() {
                screens.forEach{screen ->
                    AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
                }
            }
        }
    ) {
        BottomNavGraph(navController = navController)
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

     Scaffold(
         bottomBar = {
             NavigationBar() {
                 screens.forEach{screen ->
                    AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
                 }
             }
         }
     ) {

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
            Icon(imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true, // If route from current dst matches route passed from screen, make it selected
        onClick = {
            navController.navigate(screen.route)
        }
    )
}

@Preview
@Composable
fun MainScreenPreview(){
    MainScreen()
}