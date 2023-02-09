package com.example.hypergol.screens.wiki.launches

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.screens.common.LaunchItem


@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun LaunchesScreen(
    launchesViewModel: LaunchesViewModel = hiltViewModel(),
    onDetailClicked: (String) -> Unit
){
    val launches = launchesViewModel.launches.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        itemsIndexed(launches
        ){
                _, launch ->
            launch?.let {
                LaunchItem(launch = launch, onDetailClicked = onDetailClicked, upcoming = false)
            }
        }
    }
}