package com.example.hypergol.data.paging.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.launch.Launch
import com.example.hypergol.util.Constants.ITEMS_PER_PAGE

class LaunchSearchPagingSource(
    private val launchApi: LaunchApi,
    private val query: String
) : PagingSource<Int, Launch>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Launch> {
        val currentPage = params.key ?: 1
        return try {
            val results = launchApi.searchLaunches(query = query, limit = ITEMS_PER_PAGE)
            val launches = results.distinct()
            val endOfPaginationReached = currentPage * ITEMS_PER_PAGE >= results.count
            if (launches.isNotEmpty()) {
                LoadResult.Page(
                    data = launches,
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

    override fun getRefreshKey(state: PagingState<Int, Launch>): Int? {
        return state.anchorPosition
    }
}