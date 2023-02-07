package com.example.hypergol.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.hypergol.data.local.HypergolDatabase
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.agency.AgencyRemoteKeys
import com.example.hypergol.model.common.Agency

@ExperimentalPagingApi
class AgencyRemoteMediator(
    private val launchApi: LaunchApi,
    private val hypergolDatabase: HypergolDatabase
) : RemoteMediator<Int, Agency>() {

    private val agencyDao = hypergolDatabase.agencyDao()
    private val agencyRemoteKeysDao = hypergolDatabase.agencyRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Agency>
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

            val response = launchApi.getAgencies(offset = currentOffset, limit = 15)
            val agencies = response.results
            val endOfPaginationReached = agencies.isEmpty()

            // Offset by items per page
            val prevPage = if (currentOffset == 0) null else currentOffset - 15
            val nextPage = if (endOfPaginationReached) null else currentOffset + 15

            hypergolDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    agencyDao.deleteAllAgencies()
                    agencyRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = agencies.map { agency ->
                    AgencyRemoteKeys(
                        id = agency.id,
                        prevOffset = prevPage,
                        nextOffset = nextPage
                    )
                }
                agencyRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                agencyDao.addAgencies(agencies = agencies)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Agency>
    ): AgencyRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {id ->
                agencyRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Agency>
    ): AgencyRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {agency ->
                agencyRemoteKeysDao.getRemoteKeys(id = agency.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Agency>
    ): AgencyRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { agency ->
                agencyRemoteKeysDao.getRemoteKeys(id = agency.id)
            }
    }
}