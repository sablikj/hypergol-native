package com.example.hypergol.screens.wiki.rockets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.screens.common.LaunchItem
import com.example.hypergol.screens.common.RocketItem
import com.example.hypergol.screens.common.SearchWidget

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterialApi
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun RocketSearchScreen(
    onDetailClicked: (Int) -> Unit,
    navController: NavHostController,
    rocketsViewModel: RocketsViewModel = hiltViewModel()
) {
    var searchQuery by rocketsViewModel.searchQuery
    val results = rocketsViewModel.searchedRockets.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    searchQuery = it
                },
                onSearchClicked = {
                    rocketsViewModel.searchRockets(query = it)
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
                        i, rocket ->
                    rocket?.let {
                        RocketItem(rocket = rocket, onDetailClicked = onDetailClicked)
                    }
                }
            }
        }
    )
}