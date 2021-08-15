package com.jav1001.vinaysingh.wallster.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jav1001.vinaysingh.wallster.R
import com.jav1001.vinaysingh.wallster.data.WallPaperInfo
import com.jav1001.vinaysingh.wallster.ui.WallpaperListViewHolder.Companion.from
import java.nio.file.attribute.FileTime.from

class FavoriteWallpaperAdapter (private val FavWallpaperClickListner: OnFavWallpaperClickListner): RecyclerView.Adapter<FavWallpaperHolder>()
{
    var fav: List<WallPaperInfo> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavWallpaperHolder {
       return  FavWallpaperHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavWallpaperHolder, position: Int) {
        holder.bind(fav[position],FavWallpaperClickListner)
    }

    override fun getItemCount(): Int = fav.size


}

class FavWallpaperHolder  private constructor(itemView: View): RecyclerView.ViewHolder(itemView){


    companion object {
        fun from(parent: ViewGroup): FavWallpaperHolder {
            val layout = LayoutInflater.from(parent.context).inflate(R.layout.view_wallpaper_favorite_items, parent, false)
            return FavWallpaperHolder(layout)
        }
    }

    private val image = itemView.findViewById<ImageView>(R.id.fave_icon)
    fun bind(wallPaperInfo: WallPaperInfo, OnfavWallpaperClickListner: OnFavWallpaperClickListner) {
        //image.setImageResource(R.drawable.beach)
        itemView.setOnClickListener {
            OnfavWallpaperClickListner.onFavClick(wallPaperInfo)
        }




    }





}

class OnFavWallpaperClickListner(private  val listner: (WallPaperInfo) -> Unit) {
    fun onFavClick(wallPaperInfo: WallPaperInfo) = listner.invoke(wallPaperInfo)
}
