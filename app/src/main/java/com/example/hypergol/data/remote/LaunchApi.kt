package com.example.hypergol.data.remote

import com.example.hypergol.model.LaunchDetail
import com.example.hypergol.model.UpcomingLaunch
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LaunchApi {

    // Upcoming launches - main page
    @GET("/launch/upcoming/?mode=detailed&format=json")
    suspend fun getUpcomingLaunches(
        @Query("offset") page: Int,
        @Query("limit") per_page: Int
    ): List<UpcomingLaunch>

    // Launch detail
    @GET("/launch/{id}/?mode=detailed&format=json")
    suspend fun getLaunch(
        @Path("id") id: String
    ): LaunchDetail


}