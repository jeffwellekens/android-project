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

/**
 * Repository interface defining methods for dealing with stores.
 */
interface StoreRepository {
    /**
     * Get a Flow of all stores from the local database.
     *
     * @return A [Flow] emitting a list of [Store] objects.
     */
    fun getAll(): Flow<List<Store>>

    /**
     * Get a Flow of a store by its unique store ID from the local database.
     *
     * @param storeId The unique identifier of the store.
     * @return A [Flow] emitting the [Store] with the specified store ID.
     */
    fun getById(storeId: String): Flow<Store>
    /**
     * Refresh the stores by fetching data from the remote API and updating the local database.
     */
    suspend fun refreshStores()
}

/**
 * Offline implementation of [StoreRepository] that utilizes local database and remote API.
 *
 * @property storeService Retrofit service for interacting with the remote store API.
 * @property storeDatabase Local Room database instance for dealing with StoreEntity.
 */
class OfflineStoreRepository @Inject constructor(
    private val storeService: StoreService,
    private val storeDatabase: StoreDatabase
) : StoreRepository {
    /**
     * Get a Flow of all stores from the local database.
     */
    override fun getAll(): Flow<List<Store>> {
        return storeDatabase.storeDao.getAllStores().map { stores ->
            stores.map { it.toStore() }
        }
    }

    /**
     * Get a Flow of a store by its unique store ID from the local database.
     */
    override fun getById(storeId: String): Flow<Store> {
        return storeDatabase.storeDao.getStoreById(storeId).map { it.toStore() }
    }

    /**
     * Refresh the stores by fetching data from the remote API and updating the local database.
     */
    override suspend fun refreshStores() {
        val stores = storeService.getStores()
        storeDatabase.storeDao.upsertAll(stores.map { it.toStoreEntity() })
    }

}