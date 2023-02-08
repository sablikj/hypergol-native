package com.example.hypergol.screens.wiki.rockets.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hypergol.data.repository.Repository
import com.example.hypergol.model.rocket.Rocket
import com.example.hypergol.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RocketDetailViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val rocketId: Int? = savedStateHandle[Constants.Routes.ROCKET_DETAIL_ID]
    var rocket by mutableStateOf(Rocket())
        private set

    init {
        rocketId?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repository.refreshRocketDetail(rocketId)
                repository.getRocketDetail(it).collect {detail ->
                    withContext(Dispatchers.Main) {
                        if (detail != null) {
                            rocket = detail
                        }
                    }
                }
            }
        }
    }
}