package com.jav1001.vinaysingh.wallster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*;
import androidx.activity.*;
import com.jav1001.vinaysingh.wallster.Main.MainViewModel
import com.jav1001.vinaysingh.wallster.Main.MainViewModelFactory

import com.jav1001.vinaysingh.wallster.data.MainRepository
import com.jav1001.vinaysingh.wallster.data.database.WallpaperDatabase

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        val apiService = (application as WallPaperApplication).serviceLocator.apiService
        val database =(application as WallPaperApplication).serviceLocator.WallpaperDatabase
        val repository = MainRepository(apiService,database)
        MainViewModelFactory(repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.wallPapers.observe(this){
            Log.d("test12",viewModel.wallPapers.value.toString())
        }
    }
}