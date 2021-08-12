package com.jav1001.vinaysingh.wallster.data.database



import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalData::class],version = 2)
abstract class WallpaperDatabase: RoomDatabase(){
    abstract fun getWallpaperDao(): WallpaperDao
}