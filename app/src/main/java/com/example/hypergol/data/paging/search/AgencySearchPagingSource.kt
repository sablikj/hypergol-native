package com.example.hypergol.data.paging.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hypergol.data.remote.LaunchApi
import com.example.hypergol.model.common.Agency
import com.example.hypergol.util.Constants

class AgencySearchPagingSource(
    private val launchApi: LaunchApi,
    private val query: String
) : PagingSource<Int, Agency>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Agency> {
        val currentPage = params.key ?: 1
        return try {
            val results = launchApi.searchAgencies(query = query, limit = Constants.ITEMS_PER_PAGE)
            val agencies = results.distinct()
            val endOfPaginationReached = currentPage * Constants.ITEMS_PER_PAGE >= results.count
            if (agencies.isNotEmpty()) {
                LoadResult.Page(
                    data = agencies,
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

    override fun getRefreshKey(state: PagingState<Int, Agency>): Int? {
        return state.anchorPosition
    }
}