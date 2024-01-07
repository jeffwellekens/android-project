package com.example.androidproject.data.repositories

import com.example.androidproject.data.local.database.StoreDatabase
import com.example.androidproject.data.local.entity.DealStoreEntity
import com.example.androidproject.data.local.entity.StoreEntity
import com.example.androidproject.data.mappers.toStore
import com.example.androidproject.data.mappers.toStoreEntity
import com.example.androidproject.data.remote.api.StoreService
import com.example.androidproject.domain.model.Store
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface StoreRepository {
    fun getAll(): Flow<List<Store>>

    fun getById(storeId: String): Flow<Store>
    suspend fun refreshStores()
}

class OfflineStoreRepository @Inject constructor(
    private val storeService: StoreService,
    private val storeDatabase: StoreDatabase
) : StoreRepository {
    override fun getAll(): Flow<List<Store>> {
        return storeDatabase.storeDao.getAllStores().map { stores ->
            stores.map { it.toStore() }
        }
    }

    override fun getById(storeId: String): Flow<Store> {
        return storeDatabase.storeDao.getStoreById(storeId).map { it.toStore() }
    }

    override suspend fun refreshStores() {
        val stores = storeService.getStores()
        storeDatabase.storeDao.upsertAll(stores.map { it.toStoreEntity() })
    }

}