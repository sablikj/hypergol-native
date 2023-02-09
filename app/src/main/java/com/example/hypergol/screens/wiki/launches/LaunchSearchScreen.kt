package com.example.hypergol.screens.wiki.launches

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.screens.common.LaunchItem
import com.example.hypergol.screens.common.SearchWidget

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterialApi
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun LaunchSearchScreen(
    onDetailClicked: (String) -> Unit,
    navController: NavHostController,
    launchesViewModel: LaunchesViewModel = hiltViewModel()
) {
    var searchQuery by launchesViewModel.searchQuery
    val results = launchesViewModel.searchedLaunches.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    searchQuery = it
                },
                onSearchClicked = {
                    launchesViewModel.searchLaunches(query = it)
                }
            ) {
                navController.popBackStack()
            }
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(all = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                itemsIndexed(results
                ){
                        _, launch ->
                    launch?.let {
                        LaunchItem(launch = launch, onDetailClicked = onDetailClicked, upcoming = false)
                    }
                }
            }
        }
    )
}