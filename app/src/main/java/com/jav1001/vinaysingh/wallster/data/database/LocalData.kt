package com.jav1001.vinaysingh.wallster.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jav1001.vinaysingh.wallster.data.WallPaperInfo

@Entity(tableName = "local_data")
class LocalData (
        @PrimaryKey
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
    )
    {
        fun toDamain(): WallPaperInfo
        {
            return WallPaperInfo(
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
