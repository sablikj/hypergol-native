package com.example.hypergol.screens.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.ExperimentalPagingApi
import com.example.hypergol.data.repository.Repository
import com.example.hypergol.model.Launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class UpcomingLaunchesViewModel @Inject constructor(
    repository: Repository
): ViewModel() {
    val upcomingLaunches = repository.getUpcomingLaunches()
}