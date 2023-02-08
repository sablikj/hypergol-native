package com.example.hypergol.screens.wiki.agency

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.hypergol.data.repository.Repository
import com.example.hypergol.model.common.Agency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class AgencyViewModel@Inject constructor(
    private val repository: Repository
): ViewModel() {
    val agencies = repository.getAgencies()

    // Search
    val searchQuery = mutableStateOf("")
    private val _searchedAgencies = MutableStateFlow<PagingData<Agency>>(PagingData.empty())
    val searchedAgencies = _searchedAgencies

    fun searchAgencies(query: String) {
        viewModelScope.launch {
            repository.searchAgencies(query = query).cachedIn(viewModelScope).collect {
                _searchedAgencies.value = it
            }
        }
    }
}