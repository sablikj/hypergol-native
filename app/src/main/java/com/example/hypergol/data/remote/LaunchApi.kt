package com.example.hypergol.data.remote

import com.example.hypergol.model.launch.LaunchDetail
import com.example.hypergol.model.common.Agency
import com.example.hypergol.model.common.AgencyResponse
import com.example.hypergol.model.launch.LaunchResponse
import com.example.hypergol.model.rocket.Rocket
import com.example.hypergol.model.rocket.RocketResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LaunchApi {

    // Upcoming launches - main page
    @GET("launch/upcoming/?mode=detailed&format=json")
    suspend fun getUpcomingLaunches(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): LaunchResponse

    // All launches
    @GET("launch/?mode=detailed&format=json")
    suspend fun getLaunches(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): LaunchResponse

    // Launch detail
    @GET("launch/{id}/?mode=detailed&format=json")
    suspend fun getLaunch(
        @Path("id") id: String
    ): LaunchDetail

    // Launch search
    @GET("launch/?mode=detailed&format=json")
    suspend fun searchLaunches(
        @Query("search") query: String,
        @Query("limit") limit: Int
    ): LaunchResponse

    // All agencies
    @GET("agencies/?mode=detailed&format=json&featured=true")
    suspend fun getAgencies(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): AgencyResponse

    // Agency detail
    @GET("agencies/{id}/?mode=detailed&format=json&featured=true")
    suspend fun getAgency(
        @Path("id") id: Int
    ): Agency

    // All rockets
    @GET("config/launcher/?mode=detailed&format=json")
    suspend fun getRockets(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): RocketResponse

    // Rocket detail
    @GET("config/launcher/{id}/?mode=detailed&format=json")
    suspend fun getRocket(
        @Path("id") id: Int
    ): Rocket
}