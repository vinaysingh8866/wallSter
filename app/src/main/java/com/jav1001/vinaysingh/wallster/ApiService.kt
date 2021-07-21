package com.jav1001.vinaysingh.wallster
import com.jav1001.vinaysingh.wallster.data.WallpaperList
import retrofit2.http.GET
import retrofit2.http.Path
interface ApiService {
    @GET("search?q=vape")
    suspend fun getWallpaper():WallpaperList
//https://wallhaven.cc/api/v1/search?q=vape
}