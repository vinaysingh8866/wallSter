package com.jav1001.vinaysingh.wallster.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jav1001.vinaysingh.wallster.ApiService
import retrofit2.HttpException
import java.io.IOException

class MainRepository(private val apiService: ApiService)  {

    private val _wallpaperInfos = MutableLiveData<List<WallPaperInfo>>()
    val wallpaperInfos: LiveData<List<WallPaperInfo>> get() = _wallpaperInfos

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
            Log.d("test123",wallpaperInfos.toString())
        }
    }

}

