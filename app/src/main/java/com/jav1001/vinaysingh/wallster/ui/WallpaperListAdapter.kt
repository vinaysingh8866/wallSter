package com.jav1001.vinaysingh.wallster.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

    fun bind(wallpaper: WallPaperInfo, onWallpaperClickListner: OnWallpaperClickListner ){

        Glide.with(itemView).load(wallpaper.thumbs.small).centerInside().into(image1)
        //image1.setImageResource(R.drawable.bird)
        //image2.setImageResource(R.drawable.flower)
        //Log.d("",wallpaper.path)
        textTest.text = wallpaper.id
        itemView.setOnClickListener{
            onWallpaperClickListner.onWallpaperClick(wallpaper)
        }

    }

}

class OnWallpaperClickListner(private  val listner: (WallPaperInfo) -> Unit){
    fun onWallpaperClick(wallPaperInfo: WallPaperInfo) = listner.invoke(wallPaperInfo)

}