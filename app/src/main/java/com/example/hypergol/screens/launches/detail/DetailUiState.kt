package com.example.hypergol.screens.launches.detail

import com.example.hypergol.model.LaunchDetail
import com.example.hypergol.util.formatDate

data class DetailUiState(
    val detail: LaunchDetail? = LaunchDetail()
) {
    val formattedNet = formatDate(detail?.net)
    val net = detail?.net
}