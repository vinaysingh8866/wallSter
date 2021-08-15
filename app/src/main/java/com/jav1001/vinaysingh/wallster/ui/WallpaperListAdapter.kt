package com.jav1001.vinaysingh.wallster.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.IMultiInstanceInvalidationCallback
import com.bumptech.glide.Glide
import com.jav1001.vinaysingh.wallster.R
import com.jav1001.vinaysingh.wallster.data.WallPaperInfo

class WallpaperListAdapter(private val wallpaperClickListner: OnWallpaperClickListner):RecyclerView.Adapter<WallpaperListViewHolder>() {

    var wallpapers: List<WallPaperInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperListViewHolder {
        return WallpaperListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WallpaperListViewHolder, position: Int) {
        holder.bind(wallpapers[position], wallpaperClickListner)
    }

    override fun getItemCount(): Int = wallpapers.size


}

class WallpaperListViewHolder private constructor(itemview: View): RecyclerView.ViewHolder(itemview){

    companion object{
     fun from(parent: ViewGroup): WallpaperListViewHolder{
         val layout = LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_list_item, parent, false)
         return WallpaperListViewHolder(layout)

     }
    }

    private val image1 = itemview.findViewById<ImageView>(R.id.imageViewR1)
    //private val image2 = itemview.findViewById<ImageView>(R.id.imageViewR2)
    private val textTest = itemview.findViewById<TextView>(R.id.textTest)

    private val imageFav = itemview.findViewById<ImageView>(R.id.fave_icon)
    fun bind(wallpaper: WallPaperInfo, onWallpaperClickListner: OnWallpaperClickListner ){

        val dd = wallpaper.toLocal()
        Glide.with(itemView).load(wallpaper.thumbs.small).centerInside().into(image1)
        //image1.setImageResource(R.drawable.bird)
        //image2.setImageResource(R.drawable.flower)
        //Log.d("",wallpaper.path)

        //imageFav.setImageResource(R.drawable.ic_favorite_border)

//        imageFav.setOnClickListener {
//            imageFav.setImageResource(R.drawable.ic_favorite)
//        }
        textTest.text = wallpaper.id

        itemView.setOnClickListener{
            onWallpaperClickListner.onWallpaperClick(wallpaper)
        }

    }

}

class OnWallpaperClickListner(private  val listner: (WallPaperInfo) -> Unit){
    fun onWallpaperClick(wallPaperInfo: WallPaperInfo) = listner.invoke(wallPaperInfo)

}