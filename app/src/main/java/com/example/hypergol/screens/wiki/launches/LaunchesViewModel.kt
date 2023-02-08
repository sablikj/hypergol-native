package com.example.hypergol.screens.wiki.launches

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hypergol.data.repository.Repository
import com.example.hypergol.model.launch.Launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class LaunchesViewModel @Inject constructor(
     private val repository: Repository
): ViewModel() {
    val launches = repository.getLaunches()

    // Search
    val searchQuery = mutableStateOf("")

    private val _searchedLaunches = MutableStateFlow<PagingData<Launch>>(PagingData.empty())
    val searchedLaunches = _searchedLaunches

    fun searchLaunches(query: String) {
        viewModelScope.launch {
            repository.searchLaunches(query = query).cachedIn(viewModelScope).collect {
                _searchedLaunches.value = it
            }
        }
    }
}