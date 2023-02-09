package com.example.hypergol.screens.wiki.rockets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.example.hypergol.screens.common.RocketItem

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun RocketsScreen(rocketsViewModel: RocketsViewModel = hiltViewModel(),
                  onRocketDetail: (Int) -> Unit
){
    val rockets = rocketsViewModel.rockets.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        itemsIndexed(rockets
        ){
                _, rocket ->
            rocket?.let {
                RocketItem(rocket = rocket, onDetailClicked = onRocketDetail)
            }
        }
    }
}