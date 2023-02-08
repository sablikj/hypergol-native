package com.example.hypergol.data.paging.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.rocket.Rocket
import com.example.hypergol.util.Constants

class RocketSearchPagingSource(
    private val launchApi: LaunchApi,
    private val query: String
) : PagingSource<Int, Rocket>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Rocket> {
        val currentPage = params.key ?: 1
        return try {
            val results = launchApi.searchRockets(query = query, limit = Constants.ITEMS_PER_PAGE)
            val rockets = results.distinct()
            val endOfPaginationReached = currentPage * Constants.ITEMS_PER_PAGE >= results.count
            if (rockets.isNotEmpty()) {
                LoadResult.Page(
                    data = rockets,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Rocket>): Int? {
        return state.anchorPosition
    }
}