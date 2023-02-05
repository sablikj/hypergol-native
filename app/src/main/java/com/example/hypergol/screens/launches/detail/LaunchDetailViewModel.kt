package com.example.hypergol.screens.launches.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.hypergol.data.repository.Repository
import com.example.hypergol.util.Constants
import com.example.hypergol.util.Constants.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LaunchDetailViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val launchId: String? = savedStateHandle[Routes.LAUNCH_DETAIL_ID]
    var uiState by mutableStateOf(DetailUiState())
        private set


    init {
        launchId?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repository.refreshLaunchDetail(launchId)
                repository.getLaunchDetail(it).collect {detail ->
                    withContext(Dispatchers.Main) {
                        uiState = uiState.copy(detail = detail)
                    }
                }
            }
        }
    }
}