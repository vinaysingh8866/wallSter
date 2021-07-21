package com.jav1001.vinaysingh.wallster.data

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
            null
        } catch (e: IOException) {
            null
        } catch (e: Exception) {
            null
        }
        result?.let {
            _wallpaperInfos.value = it.results
        }
    }

}

