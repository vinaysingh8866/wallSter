package com.jav1001.vinaysingh.wallster.ui

import android.app.WallpaperInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jav1001.vinaysingh.wallster.Main.MainViewModel
import com.jav1001.vinaysingh.wallster.Main.MainViewModelFactory
import com.jav1001.vinaysingh.wallster.R
import com.jav1001.vinaysingh.wallster.WallPaperApplication
import com.jav1001.vinaysingh.wallster.data.MainRepository
import com.jav1001.vinaysingh.wallster.data.WallPaperInfo
import com.jav1001.vinaysingh.wallster.data.WallpaperList
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FavoriteActivity : AppCompatActivity() {


    private val viewModel by viewModels<FavoriteViewModel> {
        val apiService = (application as WallPaperApplication).serviceLocator.apiService
        val database = (application as WallPaperApplication).serviceLocator.WallpaperDatabase
        val repository = MainRepository(apiService, database)
        FavoriteViewModelFactory(repository)
    }

    val adapter = FavoriteWallpaperAdapter(OnFavWallpaperClickListner {
        Log.d("test12",it.id)
    })
    var listWallpaper: List<WallPaperInfo> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        findViewById<RecyclerView>(R.id.recyclerViewFevorite).also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
            adapter.fav = listWallpaper
        }

        viewModel.wallPapers.observe(this){
            listWallpaper = it.toList()
            adapter.fav = listWallpaper
            adapter.notifyDataSetChanged()
        }




    }
}