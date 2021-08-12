package com.jav1001.vinaysingh.wallster.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.jav1001.vinaysingh.wallster.ApiService
import com.jav1001.vinaysingh.wallster.data.database.WallpaperDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainRepository(private val apiService: ApiService, private val wallpaperDatabase: WallpaperDatabase)  {



    private val _wallpaperInfos = MutableLiveData<List<WallPaperInfo>>()
    val wallpaperInfos: LiveData<List<WallPaperInfo>> get() = _wallpaperInfos

    val wallpapers : LiveData<List<WallPaperInfo>> = wallpaperDatabase.getWallpaperDao().getWallpaper().map {
        it.map {
            localData -> localData.toDamain()
        }
    }

    val favWallpaper:LiveData<List<WallPaperInfo>> = wallpaperDatabase.getWallpaperDao().getFavWallpaper().map {
        it.map { localData -> localData.toDamain() }
    }



    suspend fun loadWallpaper() {
        val result: WallpaperList? = try {
            apiService.getWallpaper()
        } catch (e: HttpException) {
            Log.d("expkx",e.toString())
            null
        } catch (e: IOException) {
            Log.d("expkx",e.toString())
            null
        } catch (e: Exception) {
            Log.d("expkx",e.toString())
            null
        }
        result?.let {
            _wallpaperInfos.value = it.results
            withContext(Dispatchers.IO){
                wallpaperDatabase.getWallpaperDao().insertAll (it.results.map { it.toLocal() })
            }
        }
    }

}

