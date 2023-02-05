package com.example.hypergol.screens.wiki.launches

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.example.hypergol.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class LaunchesViewModel @Inject constructor(
    repository: Repository
): ViewModel() {
    val launches = repository.getLaunches()
}