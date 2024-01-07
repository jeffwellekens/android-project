package com.example.androidproject.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.androidproject.data.repositories.DealRepository
import com.example.androidproject.data.repositories.OfflineDealRepository
import com.example.androidproject.data.local.database.DealDatabase
import com.example.androidproject.data.local.database.StoreDatabase
import com.example.androidproject.data.remote.api.DealService
import com.example.androidproject.data.remote.api.StoreService
import com.example.androidproject.data.repositories.OfflineStoreRepository
import com.example.androidproject.data.repositories.StoreRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDealDatabase(@ApplicationContext context: Context): DealDatabase {
        return Room.databaseBuilder(
            context,
            DealDatabase::class.java,
            "deals.db"
        ).build()
    }

    private val json = Json {
        ignoreUnknownKeys = true
    }
    @Provides
    @Singleton
    fun provideDealService(): DealService {
        return Retrofit.Builder()
            .baseUrl(DealService.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDealRepository(dealDb: DealDatabase, dealService: DealService): DealRepository =
        OfflineDealRepository(dealService, dealDb)

    @Provides
    @Singleton
    fun provideStoreDatabase(@ApplicationContext context: Context): StoreDatabase {
        return Room.databaseBuilder(
            context,
            StoreDatabase::class.java,
            "stores.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideStoreService(): StoreService {
        return Retrofit.Builder()
            .baseUrl(StoreService.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create()
    }
    @Provides
    @Singleton
    fun provideStoreRepository(storeDb: StoreDatabase, storeService: StoreService): StoreRepository =
        OfflineStoreRepository(storeService, storeDb)

//    @Provides
//    @Singleton
//    fun provideDealPager(dealDb: DealDatabase, dealService: DealService): Pager<Int, DealEntity> {
//        return Pager(
//            config = PagingConfig(pageSize = 60),
//            remoteMediator = DealRemoteMediator(
//                dealDb = dealDb,
//                dealService = dealService
//            ),
//            pagingSourceFactory = {
//                dealDb.dao.pagingSource()
//            }
//        )
//    }

}