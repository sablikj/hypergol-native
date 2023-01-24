package com.example.hypergol.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.hypergol.data.local.HypergolDatabase
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.LaunchRemoteKeys
import com.example.hypergol.model.UpcomingLaunch
import com.example.hypergol.util.Constants.ITEMS_PER_PAGE
import javax.inject.Inject

@ExperimentalPagingApi
class HypergolRemoteMediator @Inject constructor(
    private val launchApi: LaunchApi,
    private val hypergolDatabase: HypergolDatabase
) : RemoteMediator<Int, UpcomingLaunch>() {

    private val upcomingLaunchDao = hypergolDatabase.upcomingLaunchDao()
    private val launchRemoteKeysDao = hypergolDatabase.launchRemoteKeysDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UpcomingLaunch>
    ): MediatorResult {
        return try {
            // Calculating current page number
            val currentPage = when (loadType) {
                // On first request to server
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    // if next page is null, set current to 1
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = launchApi.getUpcomingLaunches(page = currentPage, per_page = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            hypergolDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    upcomingLaunchDao.deleteAllUpcomingLaunches()
                    launchRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.map { upcomingLaunch ->
                    LaunchRemoteKeys(
                        id = upcomingLaunch.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                launchRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                upcomingLaunchDao.addUpcomingLaunches(launches = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UpcomingLaunch>
    ): LaunchRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {id ->
                launchRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UpcomingLaunch>
    ): LaunchRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {upcomingLaunch ->
                launchRemoteKeysDao.getRemoteKeys(id = upcomingLaunch.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UpcomingLaunch>
    ): LaunchRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { upcomingLaunch ->
                launchRemoteKeysDao.getRemoteKeys(id = upcomingLaunch.id)
            }
    }
}