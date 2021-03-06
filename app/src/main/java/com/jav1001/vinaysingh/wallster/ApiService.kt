package com.jav1001.vinaysingh.wallster
import com.jav1001.vinaysingh.wallster.data.WallpaperList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getWallpaper(@Query("q") queryKey: String = "",
                             @Query("categories") categories: String):WallpaperList
//https://wallhaven.cc/api/v1/search?q=vape
}