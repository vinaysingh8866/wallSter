package com.jav1001.vinaysingh.wallster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*;
import androidx.activity.*;
import com.jav1001.vinaysingh.wallster.Main.MainViewModel
import com.jav1001.vinaysingh.wallster.Main.MainViewModelFactory

import com.jav1001.vinaysingh.wallster.data.MainRepository
import com.jav1001.vinaysingh.wallster.data.database.WallpaperDatabase
import com.jav1001.vinaysingh.wallster.databinding.ActivityMainBinding
import com.jav1001.vinaysingh.wallster.ui.FavoriteActivity
import com.jav1001.vinaysingh.wallster.ui.ListActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        val apiService = (application as WallPaperApplication).serviceLocator.apiService
        val database =(application as WallPaperApplication).serviceLocator.WallpaperDatabase
        val repository = MainRepository(apiService,database)
        MainViewModelFactory(repository)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.wallPapers.observe(this){
            Log.d("test12",viewModel.wallPapers.value.toString())
        }

        binding.buttonPenguin.setOnClickListener {
            val intent = Intent(this,  ListActivity::class.java).apply {

            }
            startActivity(intent)
        }


        binding.favoriteButton.setOnClickListener {
            val intent = Intent(this,  FavoriteActivity::class.java).apply {

            }
            startActivity(intent)
        }





    }
}