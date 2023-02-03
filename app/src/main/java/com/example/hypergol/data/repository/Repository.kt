package com.example.hypergol.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.hypergol.data.local.HypergolDatabase
import com.example.hypergol.data.paging.HypergolRemoteMediator
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.Launch
import com.example.hypergol.model.LaunchDetail
import com.example.hypergol.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository  @Inject constructor(
    private val launchApi: LaunchApi,
    private val hypergolDatabase: HypergolDatabase
){
    // Called from Main screen, returning flow of data
    // Data are coming from database, not API directly
    @ExperimentalPagingApi
    fun getUpcomingLaunches(): Flow<PagingData<Launch>> {
        val pagingSourceFactory = { hypergolDatabase.upcomingLaunchDao().getAllUpcomingLaunches() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HypergolRemoteMediator(
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
}