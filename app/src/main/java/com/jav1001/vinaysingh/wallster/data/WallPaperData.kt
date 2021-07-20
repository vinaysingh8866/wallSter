package com.jav1001.vinaysingh.wallster.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class WallPaperData {
    @JsonClass(generateAdapter = true)

    data class WallpaperSearch(
        @Json(name = "data")
        val results://
    )


    data class WallPaperInfo(
        val id: Int,
        val url: String,
        val short_url: Int,
        views Int
        favorites Int,
        source,
        purity,
        category,
        dimension_x Int,
        dimension_y Int,
        resolution,
        ratio,
        file_size Int,
        file_type,
        created_at,
        val colors : List<String>,
        path:,

    )



}