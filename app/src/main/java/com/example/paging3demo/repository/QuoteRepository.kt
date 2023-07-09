package com.example.paging3demo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.paging3demo.paging.QuotePagingSource
import com.example.paging3demo.retrofit.QuoteApi
import javax.inject.Inject

class QuoteRepository @Inject constructor(val quoteApi: QuoteApi) {

    fun getQuotes()= Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {QuotePagingSource(quoteApi)}
    ).liveData
}