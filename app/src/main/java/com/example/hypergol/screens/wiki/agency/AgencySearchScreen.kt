package com.example.hypergol.screens.wiki.agency

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
import com.example.hypergol.screens.common.AgencyItem
import com.example.hypergol.screens.common.SearchWidget
import com.example.hypergol.screens.wiki.rockets.RocketsViewModel

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterialApi
@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun AgencySearchScreen(
    onDetailClicked: (Int) -> Unit,
    navController: NavHostController,
    agencyViewModel: AgencyViewModel = hiltViewModel()
) {
    var searchQuery by agencyViewModel.searchQuery
    val results = agencyViewModel.searchedAgencies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchWidget(
                text = searchQuery,
                onTextChange = {
                    searchQuery = it
                },
                onSearchClicked = {
                    agencyViewModel.searchAgencies(query = it)
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
                        i, agency ->
                    agency?.let {
                        AgencyItem(agency = agency, onDetailClicked = onDetailClicked)
                    }
                }
            }
        }
    )
}