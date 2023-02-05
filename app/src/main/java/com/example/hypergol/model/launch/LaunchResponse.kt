package com.example.hypergol.model.launch

import kotlinx.serialization.Serializable

@Serializable
data class LaunchResponse(val results: List<Launch>)