package com.example.hypergol.screens.wiki.agency.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hypergol.data.repository.Repository
import com.example.hypergol.model.common.Agency
import com.example.hypergol.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AgencyDetailViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val loading = mutableStateOf(false)
    private val agencyId: Int? = savedStateHandle[Constants.Routes.LSP_DETAIL_ID]
    var agencyDetail by mutableStateOf(Agency())
        private set

    init {
        agencyId?.let {
            loading.value = true
            viewModelScope.launch(Dispatchers.IO) {
                repository.refreshAgencyDetail(agencyId)
                repository.getAgencyDetail(it).collect {detail ->
                    withContext(Dispatchers.Main) {
                        if (detail != null) {
                            agencyDetail = detail
                            loading.value = false
                        }
                    }
                }
            }
        }
    }
}