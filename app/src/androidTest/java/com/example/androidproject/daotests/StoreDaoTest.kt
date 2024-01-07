package com.example.androidproject.daotests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.androidproject.daotests.fake.FakeDataSource
import com.example.androidproject.data.local.dao.StoreDao
import com.example.androidproject.data.local.database.StoreDatabase
import com.example.androidproject.data.mappers.toStore
import com.example.androidproject.data.mappers.toStoreEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class StoreDaoTest {
    private lateinit var storeDao: StoreDao
    private lateinit var database: StoreDatabase

    private val storeList = listOf(
        FakeDataSource.store1,
        FakeDataSource.store2,
        FakeDataSource.store3
    )

    private suspend fun addItemsToDb() {
        storeDao.upsertAll(storeList.map { it.toStoreEntity() })
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, StoreDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        storeDao = database.storeDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertStoresIntoDb() = runBlocking {
        addItemsToDb()
        val stores = storeDao.getAllStores().first().map { it.toStore() }

        Assert.assertEquals(storeList[0], stores[0])
        Assert.assertEquals(storeList[1], stores[1])
        Assert.assertEquals(storeList[2], stores[2])
    }

    @Test
    @Throws(Exception::class)
    fun getStoreById() = runBlocking {
        addItemsToDb()
        val store = storeDao.getStoreById("1").first().toStore()

        Assert.assertEquals(storeList[0], store)
    }

    @Test
    @Throws(Exception::class)
    fun clearAllStoresFromDb() = runBlocking {
        addItemsToDb()
        storeDao.clearAll()
        val stores = storeDao.getAllStores().first().map { it.toStore() }

        Assert.assertEquals(0, stores.size)
    }
}