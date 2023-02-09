package com.example.hypergol.screens.wiki.rockets

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hypergol.data.repository.Repository
import com.example.hypergol.model.rocket.Rocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class RocketsViewModel@Inject constructor(
    private val repository: Repository
): ViewModel() {
    val rockets = repository.getRockets()

    // Search
    val searchQuery = mutableStateOf("")
    private val _searchedRockets = MutableStateFlow<PagingData<Rocket>>(PagingData.empty())
    val searchedRockets = _searchedRockets

    fun searchRockets(query: String) {
        viewModelScope.launch {
            repository.searchRockets(query = query).cachedIn(viewModelScope).collect {
                _searchedRockets.value = it
            }
        }
    }
}