package com.example.hypergol.screens.wiki.rockets

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.example.hypergol.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class RocketsViewModel@Inject constructor(
    repository: Repository
): ViewModel() {
    val rockets = repository.getRockets()
}