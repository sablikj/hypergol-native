package com.example.hypergol.model

import kotlinx.serialization.Serializable

@Serializable
data class UpcomingLaunchResponse(val results: List<Launch>)