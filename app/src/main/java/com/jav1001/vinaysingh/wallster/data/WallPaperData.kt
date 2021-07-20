package com.jav1001.vinaysingh.wallster.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)

data class WallpaperSearch(
    @Json(name = "data")
    val results: List<WallPaperInfo>//
)

@JsonClass(generateAdapter = true)

data class WallPaperInfo(
    val id: Int,
    val url: String,
    val short_url: Int,
    val views: Int,
    val favorites: Int,
    val source: Int,
    val purity: Int,
    val category: String,
    val dimension_x: Int,
    val dimension_y: Int,
    val resolution: String,
    val ratio: String,
    val file_size: String,
    val file_type: String,
    val created_at: String,
    val colors : List<String>,
    val path: String,

)



