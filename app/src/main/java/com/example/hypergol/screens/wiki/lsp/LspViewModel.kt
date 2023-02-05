package com.example.hypergol.screens.wiki.lsp

import androidx.lifecycle.ViewModel
import com.example.hypergol.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LspViewModel@Inject constructor(
    repository: Repository
): ViewModel() {

}