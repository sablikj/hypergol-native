package com.example.hypergol.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.hypergol.data.local.HypergolDatabase
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.rocket.Rocket
import com.example.hypergol.model.rocket.RocketRemoteKeys
import com.example.hypergol.util.Constants.ITEMS_PER_PAGE

@ExperimentalPagingApi
class RocketRemoteMediator(
    private val launchApi: LaunchApi,
    private val hypergolDatabase: HypergolDatabase
) : RemoteMediator<Int, Rocket>() {

    private val rocketDao = hypergolDatabase.rocketDetailDao()
    private val rocketRemoteKeysDao = hypergolDatabase.rocketRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Rocket>
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
            val response = launchApi.getRockets(offset = currentOffset, limit = ITEMS_PER_PAGE)
            val rockets = response.results
            val endOfPaginationReached = rockets.isEmpty()

            // Offset by items per page
            val prevPage = if (currentOffset == 0) null else currentOffset - ITEMS_PER_PAGE
            val nextPage = if (endOfPaginationReached) null else currentOffset + ITEMS_PER_PAGE

            hypergolDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    rocketDao.deleteAllRockets()
                    rocketRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = rockets.map { rocket ->
                    RocketRemoteKeys(
                        id = rocket.id,
                        prevOffset = prevPage,
                        nextOffset = nextPage
                    )
                }
                rocketRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                rocketDao.addRockets(rockets = rockets)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Rocket>
    ): RocketRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {id ->
                rocketRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Rocket>
    ): RocketRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {rocket ->
                rocketRemoteKeysDao.getRemoteKeys(id = rocket.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Rocket>
    ): RocketRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { rocket ->
                rocketRemoteKeysDao.getRemoteKeys(id = rocket.id)
            }
    }
}