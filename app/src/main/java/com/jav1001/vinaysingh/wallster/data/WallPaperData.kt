package com.jav1001.vinaysingh.wallster.data

import com.jav1001.vinaysingh.wallster.data.database.LocalData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)

data class WallpaperList(
    @Json(name = "data")
    val results: List<WallPaperInfo>
)
@JsonClass(generateAdapter = true)
data class WallPaperInfo(
    val id: String,
    val url: String,
    val short_url: String,
    val views: Int,
    val favorites: Int,
    val source: String,
    val purity: String,
    val category: String,
    val dimension_x: Int,
    val dimension_y: Int,
    val resolution: String,
    val ratio: String,
    val file_size: String,
    val file_type: String,
    val created_at: String,

    val path: String,
    var isfaved:Boolean=false


){
    fun toLocal() :LocalData {
        return LocalData(
            id,
            url,
            short_url,
            views,
            favorites,
            source,
            purity,
            category,
            dimension_x,
            dimension_y,
            resolution,
            ratio,
            file_size,
            file_type,
            created_at,
            path
        )
    }
}





