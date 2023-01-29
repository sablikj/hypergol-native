package com.example.hypergol.screens.launches

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.screens.common.ListContent


@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun UpcomingLaunchesScreen(
    upcomingLaunchesViewModel: UpcomingLaunchesViewModel = hiltViewModel()
){
    val getAllUpcomingLaunches = upcomingLaunchesViewModel.getAllUpcomingLaunches.collectAsLazyPagingItems()
        
    ListContent(itemsList = getAllUpcomingLaunches)
}