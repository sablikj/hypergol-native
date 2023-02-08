package com.example.hypergol.screens.launches.detail

import com.example.hypergol.model.launch.LaunchDetail
import com.example.hypergol.util.formatDate

data class DetailUiState(
    val detail: LaunchDetail? = LaunchDetail()
) {
    val formattedNet = formatDate(detail?.net, false)
    val net = detail?.net
}