package com.example.hypergol.screens.launches

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.filter
import com.example.hypergol.data.repository.Repository
import com.example.hypergol.util.isUpcoming
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class UpcomingLaunchesViewModel @Inject constructor(
    repository: Repository
): ViewModel() {
    val upcomingLaunches = repository.getUpcomingLaunches().map {
        it.filter {
            // Filtering launches from last 24h
            item -> isUpcoming(item.net.toString())
        }
    }

}