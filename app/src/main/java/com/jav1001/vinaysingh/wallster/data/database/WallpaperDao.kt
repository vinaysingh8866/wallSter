package com.jav1001.vinaysingh.wallster.data.database
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WallpaperDao {


    @Query("SELECT * FROM local_data")
    fun getWallpaper(): LiveData<List<LocalData>>

    @Query("SELECT * FROM LOCAL_DATA WHERE isfaved=1")
    fun getFavWallpaper(): LiveData<List<LocalData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(wallpapers: List<LocalData>)

    @Update
    fun update(getWallpaper: LocalData)
}