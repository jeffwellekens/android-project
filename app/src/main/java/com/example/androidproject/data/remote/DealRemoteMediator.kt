package com.example.androidproject.data.remote

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.androidproject.data.local.DealDatabase
import com.example.androidproject.data.local.DealEntity
import com.example.androidproject.data.mappers.toDealEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class DealRemoteMediator(
    private val query: MutableState<String>? = null,
    private val dealDb: DealDatabase,
    private val dealService: DealService
) : RemoteMediator<Int, DealEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, DealEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            val deals = dealService.getDeals(
                query = query!!.value,
                pageNumber = loadKey,
                pageSize = state.config.pageSize
            )
//            Log.d("DealRemoteMediator", query.value)
            dealDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dealDb.dao.clearAll()
                }
                val dealEntities = deals.map { it.toDealEntity() }
                dealDb.dao.upsertAll(dealEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = deals.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}