package com.example.hypergol.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.hypergol.data.local.HypergolDatabase
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.launch.Launch
import com.example.hypergol.model.launch.LaunchRemoteKeys
import com.example.hypergol.util.Constants.ITEMS_PER_PAGE

@ExperimentalPagingApi
class LaunchRemoteMediator(
    private val launchApi: LaunchApi,
    private val hypergolDatabase: HypergolDatabase
) : RemoteMediator<Int, Launch>() {

    private val launchDao = hypergolDatabase.launchDao()
    private val launchRemoteKeysDao = hypergolDatabase.launchRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Launch>
    ): MediatorResult {
        return try {
            // Calculating current page number
            val currentOffset = when (loadType) {
                // On first request to server
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    // if next page is null, set current to 1
                    remoteKeys?.nextOffset?.minus(1) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevOffset
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextOffset
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = launchApi.getLaunches(offset = currentOffset, limit = ITEMS_PER_PAGE)
            val launches = response.results
            val endOfPaginationReached = launches.isEmpty()

            // Offset by items per page
            val prevPage = if (currentOffset == 0) null else currentOffset - ITEMS_PER_PAGE
            val nextPage = if (endOfPaginationReached) null else currentOffset + ITEMS_PER_PAGE

            hypergolDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    launchDao.deleteAllUpcomingLaunches()
                    launchRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = launches.map { launch ->
                    LaunchRemoteKeys(
                        id = launch.id,
                        prevOffset = prevPage,
                        nextOffset = nextPage
                    )
                }
                launchRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                launchDao.addLaunches(launches = launches)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Launch>
    ): LaunchRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {id ->
                launchRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Launch>
    ): LaunchRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {upcomingLaunch ->
                launchRemoteKeysDao.getRemoteKeys(id = upcomingLaunch.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Launch>
    ): LaunchRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { upcomingLaunch ->
                launchRemoteKeysDao.getRemoteKeys(id = upcomingLaunch.id)
            }
    }
}