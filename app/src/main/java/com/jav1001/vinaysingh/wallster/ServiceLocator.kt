package com.jav1001.vinaysingh.wallster

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ServiceLocator(applicationContext: Context) {


    companion object {
        private const val BASE_URL = "https://wallhaven.cc/api/v1/"
        private const val DB_NAME = "wallpaper_database"
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
    val WallpaperDatabase = Room.databaseBuilder(
        applicationContext,
        com.jav1001.vinaysingh.wallster.data.database.WallpaperDatabase::class.java,
        DB_NAME

    ).fallbackToDestructiveMigration().build()
}