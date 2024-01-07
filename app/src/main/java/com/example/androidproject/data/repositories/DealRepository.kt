package com.example.androidproject.data.repositories

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.androidproject.data.local.database.DealDatabase
import com.example.androidproject.data.local.entity.DealEntity
import com.example.androidproject.data.mappers.toDealDetail
import com.example.androidproject.data.mappers.toDealDetailEntity
import com.example.androidproject.data.remote.mediator.DealRemoteMediator
import com.example.androidproject.data.remote.api.DealService
import com.example.androidproject.domain.model.DealDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository interface defining methods for dealing with deals and their details.
 */
interface DealRepository {
    /**
     * Get a Flow of [PagingData] containing deals from the local database and remote API.
     *
     * @param query Optional search query for filtering deals by title.
     * @return A [Flow] of [PagingData] containing [DealEntity] objects.
     */
    fun getDeals(query: MutableState<String>? = null): Flow<PagingData<DealEntity>>

    /**
     * Get a Flow of [DealDetail] for a specific deal ID.
     *
     * @param dealId The unique identifier of the deal.
     * @return A [Flow] emitting the [DealDetail] for the specified deal ID.
     */
    fun getDealByDealId(dealId: String): Flow<DealDetail>

    /**
     * Insert the details of a deal with the specified deal ID into the local database.
     *
     * @param dealId The unique identifier of the deal.
     */
    suspend fun insertDealDetailByDealId(dealId: String)
}

/**
 * Offline implementation of [DealRepository] that utilizes local database and remote API.
 *
 * @property dealService Retrofit service for interacting with the remote deal API.
 * @property dealDatabase Local Room database instance for dealing with DealEntity and DealDetailEntity.
 */
class OfflineDealRepository @Inject constructor(
    private val dealService: DealService,
    private val dealDatabase: DealDatabase
) : DealRepository {

    /**
     * Search deals whose title match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    override fun getDeals(query: MutableState<String>?): Flow<PagingData<DealEntity>> {
        val pagingSourceFactory = { dealDatabase.dealDao.pagingSource() }

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

    /**
     * Get a Flow of [DealDetail] for a specific deal ID.
     */
    override fun getDealByDealId(dealId: String): Flow<DealDetail> {
        return dealDatabase.dealDetailDao.getDealDetail(dealId).map { dealDetail ->
            //This is null when it's the first time it's getting called from the api after it is not null anymore
            Log.d("getDealByDealId", "$dealDetail")
            dealDetail.toDealDetail()
        }
    }

    /**
     * Insert the details of a deal with the specified deal ID into the local database.
     */
    override suspend fun insertDealDetailByDealId(dealId: String) {
        withContext(Dispatchers.IO) {
            if (!dealDatabase.dealDetailDao.dealDetailExists(dealId)) {
                val dealDetailEntity = dealService.getDealDetail(dealId).toDealDetailEntity(dealId)
                dealDatabase.dealDetailDao.insert(
                    dealDetailEntity
                )
            }
        }
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 60
    }


}