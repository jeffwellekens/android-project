package com.example.androidproject.data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.androidproject.data.local.DealDatabase
import com.example.androidproject.data.local.DealEntity
import com.example.androidproject.data.remote.DealRemoteMediator
import com.example.androidproject.data.remote.DealService
import kotlinx.coroutines.flow.Flow

class DealRepository(
    private val dealService: DealService,
    private val dealDatabase: DealDatabase
) {
    /**
     * Search repositories whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    fun getDeals(query: MutableState<String>? = null): Flow<PagingData<DealEntity>> {
        // appending '%' so we can allow other characters to be before and after the query string
//        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { dealDatabase.dao.pagingSource() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = DealRemoteMediator(
                query,
                dealDatabase,
                dealService,
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 60
    }
}