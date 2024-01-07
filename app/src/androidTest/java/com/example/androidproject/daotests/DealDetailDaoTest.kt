package com.example.androidproject.daotests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.androidproject.daotests.fake.FakeDataSource
import com.example.androidproject.data.local.dao.DealDetailDao
import com.example.androidproject.data.local.database.DealDatabase
import com.example.androidproject.data.mappers.toDealDetail
import com.example.androidproject.data.mappers.toDealDetailEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DealDetailDaoTest {
    private lateinit var dealDetailDao: DealDetailDao
    private lateinit var database: DealDatabase

    private val dealList = listOf(
        FakeDataSource.dealDetail1
    )

    private suspend fun addItemsToDb() {
        dealDetailDao.insert(dealList.map { it.toDealDetailEntity("1") }[0])
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, DealDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dealDetailDao = database.dealDetailDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertDealIntoDb() = runBlocking {
        addItemsToDb()
        val deal =  dealDetailDao.getDealDetail("1").first()

        assertEquals(dealList[0], deal.toDealDetail())
    }

    @Test
    @Throws(Exception::class)
    fun checkIfDealExists() = runBlocking {
        addItemsToDb()
        val dealExists =  dealDetailDao.dealDetailExists("1")

        assertEquals(true, dealExists)
    }

    @Test
    @Throws(Exception::class)
    fun clearAllDealsFromDb() = runBlocking {
        addItemsToDb()
        dealDetailDao.clearAll()
        val dealExists = dealDetailDao.dealDetailExists("1")

        assertEquals(false, dealExists)
    }
}