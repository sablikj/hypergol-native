package com.example.hypergol.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.hypergol.data.local.HypergolDatabase
import com.example.hypergol.data.paging.AgencyRemoteMediator
import com.example.hypergol.data.paging.LaunchRemoteMediator
import com.example.hypergol.data.paging.RocketRemoteMediator
import com.example.hypergol.data.paging.UpcomingLaunchRemoteMediator
import com.example.hypergol.data.paging.search.LaunchSearchPagingSource
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.launch.Launch
import com.example.hypergol.model.launch.LaunchDetail
import com.example.hypergol.model.common.Agency
import com.example.hypergol.model.rocket.Rocket
import com.example.hypergol.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository  @Inject constructor(
    private val launchApi: LaunchApi,
    private val hypergolDatabase: HypergolDatabase
){
    // Called from Main screen, returning flow of data
    // Data are coming from database, not API directly
    @ExperimentalPagingApi
    fun getUpcomingLaunches(): Flow<PagingData<Launch>> {
        val pagingSourceFactory = { hypergolDatabase.launchDao().getAllUpcomingLaunches() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UpcomingLaunchRemoteMediator(
                launchApi = launchApi,
                hypergolDatabase = hypergolDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    @ExperimentalPagingApi
    fun getLaunches(): Flow<PagingData<Launch>> {
        val pagingSourceFactory = { hypergolDatabase.launchDao().getLaunches() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = LaunchRemoteMediator(
                launchApi = launchApi,
                hypergolDatabase = hypergolDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun getLaunchDetail(launchId: String): Flow<LaunchDetail?> =
        hypergolDatabase.launchDetailDao().getLaunchDetail(launchId)

    suspend fun refreshLaunchDetail(id: String) {
        try {
            val launch: LaunchDetail = launchApi.getLaunch(id)
            hypergolDatabase.launchDetailDao().insert(launch)
        }catch (e: Exception){
            Log.d("ErrorLaunchDetail", e.toString())
        }
    }

    // Search is not cached
    fun searchLaunches(query: String): Flow<PagingData<Launch>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                LaunchSearchPagingSource(launchApi = launchApi, query = query)
            }
        ).flow
    }

    // Agency
    @ExperimentalPagingApi
    fun getAgencies(): Flow<PagingData<Agency>> {
        val pagingSourceFactory = { hypergolDatabase.agencyDao().getAgencies() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = AgencyRemoteMediator(
                launchApi = launchApi,
                hypergolDatabase = hypergolDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun getAgencyDetail(agencyId: Int): Flow<Agency?> =
        hypergolDatabase.agencyDao().getAgencyDetail(agencyId)

    suspend fun refreshAgencyDetail(id: Int) {
        try {
            val agency: Agency = launchApi.getAgency(id)
            hypergolDatabase.agencyDao().insert(agency)
        }catch (e: Exception){
            Log.d("Error", e.toString())
        }
    }

    // Rocket
    @ExperimentalPagingApi
    fun getRockets(): Flow<PagingData<Rocket>> {
        val pagingSourceFactory = { hypergolDatabase.rocketDetailDao().getRockets() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = RocketRemoteMediator(
                launchApi = launchApi,
                hypergolDatabase = hypergolDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun getRocketDetail(rocketId: Int): Flow<Rocket?> =
        hypergolDatabase.rocketDetailDao().getRocketDetail(rocketId)

    suspend fun refreshRocketDetail(id: Int) {
        try {
            val rocket: Rocket = launchApi.getRocket(id)
            hypergolDatabase.rocketDetailDao().insert(rocket)
        }catch (e: Exception){
            Log.d("Error", e.toString())
        }
    }
}