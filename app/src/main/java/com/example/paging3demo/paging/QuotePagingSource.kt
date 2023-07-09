package com.example.paging3demo.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3demo.models.Result
import com.example.paging3demo.retrofit.QuoteApi
import java.lang.Exception

class QuotePagingSource(val quoteApi: QuoteApi) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val position = params.key ?: 1
            val response = quoteApi.getQuotes(position)
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

}