package com.example.hypergol.screens.wiki.astronauts

import androidx.lifecycle.ViewModel
import com.example.hypergol.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AstronautsViewModel@Inject constructor(
    repository: Repository
): ViewModel() {

}